package com.T3_relaciones.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.T3_relaciones.Entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

}
 	 	