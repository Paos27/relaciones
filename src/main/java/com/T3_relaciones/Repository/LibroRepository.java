package com.T3_relaciones.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.T3_relaciones.Entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{

}
