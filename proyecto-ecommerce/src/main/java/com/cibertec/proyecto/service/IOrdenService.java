package com.cibertec.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.cibertec.proyecto.entity.Orden;
import com.cibertec.proyecto.entity.Usuario;

public interface IOrdenService {
	List<Orden> findAll();
	Optional<Orden> findById(Integer id);
	Orden save (Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario (Usuario usuario);
}
