package com.cibertec.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.entity.Orden;
import com.cibertec.proyecto.entity.Usuario;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {
	List<Orden> findByUsuario (Usuario usuario);
}
