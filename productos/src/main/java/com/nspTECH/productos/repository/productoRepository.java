package com.nspTECH.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nspTECH.productos.model.producto;

public interface productoRepository extends JpaRepository<producto, Long>{

}
