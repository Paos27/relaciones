package com.T3_relaciones.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.T3_relaciones.Entity.Estudiante;

@Repository
public interface EstudianteRepository  extends JpaRepository<Estudiante, Integer>{

}
