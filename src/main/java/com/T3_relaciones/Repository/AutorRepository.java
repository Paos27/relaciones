package com.T3_relaciones.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.T3_relaciones.Entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
	 
    // Consulta personalizada  para realizar un JOIN entre Autor, Autor_Categoria y Categoria
    @Query("select a.nombre, c.nombre "
         + "from Autor a " 
         + "inner join a.categorias c") 
    List<Object[]> findAutoresLibrosYCategorias();
    
 // Consulta personalizada  JOIN entre Autor, Libro, 
    @Query("select a.nombre, c.nombre "
         + "from Autor a " 
         + "inner join a.libro c") 
    List<Object[]> findAutoresLibros();
}
