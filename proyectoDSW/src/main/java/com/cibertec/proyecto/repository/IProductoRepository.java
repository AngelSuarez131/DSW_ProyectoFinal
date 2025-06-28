package com.cibertec.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.entity.Producto;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
