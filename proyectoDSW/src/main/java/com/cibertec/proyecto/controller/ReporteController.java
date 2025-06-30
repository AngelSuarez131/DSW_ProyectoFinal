//package com.cibertec.proyecto.controller;
//
//import java.util.Map;
//import java.io.ByteArrayInputStream;
//
//import com.cibertec.proyecto.service.IReporteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/reportes")
//public class ReporteController {
//
//	@Autowired
//	private IReporteService reporteService;
//
//	@GetMapping({"/ver"})
//	public String reporte(Model model) {
//		System.out.println("ReporteController - ver");
//		model.addAttribute("titulo", "Reportes del Sistema");
//		model.addAttribute("subtitulo", "Reporte - Listado de Ordenes");
//		return "administrador/reporteProducto";
//	}
//
//
//	@GetMapping("/report")
//	public ResponseEntity<InputStreamResource> verReporte(@RequestParam Map<String, Object> parameters) {
//		System.out.println("ReporteController - report");
//		try {
//			byte[] data = reporteService.generaReporte("GamerReach", parameters);
//			InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(data));
//
//			return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report.pdf")
//					.contentType(MediaType.APPLICATION_PDF)
//					.contentLength(data.length)
//					.body(resource);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.status(500).build();
//	}
//
//
//	@GetMapping("/reportPreview")
//	public String previewReporte(@RequestParam Map<String, Object> parameters, Model model) {
//		try {
//			String htmlReport = reporteService.generaReportetHtml("GamerReach", parameters);
//			model.addAttribute("titulo", "Reportes del Sistema");
//			model.addAttribute("subtitulo", "Reporte - Previsualizacion de Reporte");
//            model.addAttribute("reporte", htmlReport);
//
//			// Agregar los parámetros al modelo para utilizarlos en los campos hidden
//			model.addAttribute("parameter1", parameters.get("parameter1"));
//			model.addAttribute("parameter2", parameters.get("parameter2"));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		return "administrador/reportePreview";
//	}
//}
