package com.cibertec.proyecto.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
	private DataSource dataSource;

	@Override
	public byte[] generaReporte(String reportName, Map<String, Object> parameters) throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
		String formattedDateTime = localDateTime.format(formatter);

		parameters.put("FECHA_REPORTE", "Fecha de Reporte: Hoy, " + formattedDateTime);
		parameters.put("REPOR_DIR", "classpath:/reportes/");
		parameters.put("P_DESCRIPCION", parameters.get("parameter1"));

		// Depuración de parámetros
		System.out.println("parameter1: " + parameters.get("parameter1"));
		System.out.println("parameter2: " + parameters.get("parameter2"));

		// Manejar el caso en el que parameter2 sea null
		String precioStr = (String) parameters.get("parameter2");
		if (precioStr != null && !precioStr.trim().isEmpty()) {
			parameters.put("P_PRECIO", Double.parseDouble(precioStr.trim()));
		} else {
			parameters.put("P_PRECIO", null);
		}

		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/" + reportName + ".jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@Override
	public String generaReportetHtml(String reportName, Map<String, Object> parameters) throws Exception {
		// Obtener Fecha y Hora actual para el reporte
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
		String formattedDateTime = localDateTime.format(formatter);

		// Pasando parámetros al jasper
		parameters.put("FECHA_REPORTE", "Fecha de Reporte: Hoy, " + formattedDateTime);
		parameters.put("REPOR_DIR", "classpath:/reportes/");
		parameters.put("P_DESCRIPCION", parameters.get("parameter1"));
		String precioStr = (String) parameters.get("parameter2");
		if (precioStr != null && !precioStr.trim().isEmpty()) {
			parameters.put("P_PRECIO", Double.parseDouble(precioStr.trim()));
		} else {
			parameters.put("P_PRECIO", null);
		}

		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/" + reportName + ".jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

		HtmlExporter exporter = new HtmlExporter();
		ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(htmlStream));

		SimpleHtmlReportConfiguration reportConfig = new SimpleHtmlReportConfiguration();
		SimpleHtmlExporterConfiguration exportConfig = new SimpleHtmlExporterConfiguration();

		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		exporter.exportReport();

		return htmlStream.toString("UTF-8");
	}

}
