package com.T3_relaciones.Test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.T3_relaciones.Entity.Autor;
import com.T3_relaciones.Entity.Categoria;
import com.T3_relaciones.Entity.Libro;
import com.T3_relaciones.Repository.AutorRepository;
import com.T3_relaciones.Repository.CategoriaRepository;
import com.T3_relaciones.Repository.LibroRepository;

@Controller
public class Prueba {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método para inicializar los datos (esto puede ser llamado en algún punto inicial de la aplicación)
    @Transactional
    public void insertarDatosDePrueba() throws Exception {
        // Crear autores
        Autor autor = new Autor();
        autor.setNombre("Pablo Neruda");

        Autor autor2 = new Autor();
        autor2.setNombre("Paulo Coelho");

        // Crear libros
        Libro libro1 = new Libro();
        libro1.setTitulo("LA ESPADA ENCENDIDA");

        Libro libro2 = new Libro();
        libro2.setTitulo("CONFIESO QUE HE VIVIDO");

        // Asociar libros al autor
        autor.getLibros().add(libro1);
        autor.getLibros().add(libro2);

        // Guardar autores (esto también guardará los libros porque se está usando CascadeType.ALL)
        autorRepository.save(autor);
        autorRepository.save(autor2);

        // Crear categorías
        Categoria categoria1 = new Categoria();
        categoria1.setNombre("Ficción");

        Categoria categoria2 = new Categoria();
        categoria2.setNombre("Historia");

        // Guardar categorías
        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);

        // Asociar categorías a autores (relación muchos a muchos)
        autor.getCategorias().add(categoria1); 
        autor.getCategorias().add(categoria2);

        autor2.getCategorias().add(categoria1); 
        autor2.getCategorias().add(categoria2);

        // Guardar nuevamente los autores para que se actualicen las relaciones
        autorRepository.save(autor);
        autorRepository.save(autor2);

        System.out.println("Fin de insertar desde prueba");
    }

    // Método que maneja la consulta y la pasa a la vista
    @Transactional
    public String ejecutarConsultaConRepositorio(Model model) {
        // Llamar al método del repositorio para obtener los resultados
        List<Object[]> resultados = autorRepository.findAutoresLibrosYCategorias();

        // Pasar los resultados al modelo para que estén disponibles en la vista
        model.addAttribute("resultados", resultados);

        // Retornar el nombre de la vista HTML (Thymeleaf)
        return "autores";  // Este es el nombre del archivo autores.html
    }
    
    @Transactional
    public String ejecutarConsultaConRepositorioLibros(Model model) {
        // Llamar al método del repositorio para obtener los resultados
        List<Object[]> resultados = autorRepository.findAutoresLibros();

        // Pasar los resultados al modelo para que estén disponibles en la vista
        model.addAttribute("resultados", resultados);

        // Retornar el nombre de la vista HTML (Thymeleaf)
        return "autoresLibros";  // Este es el nombre del archivo autores.html
    }

    // Método que se puede mapear con un @GetMapping para manejar una petición HTTP
    @GetMapping("/autores")
    public String verAutores(Model model) {
        // Llamar a insertarDatosDePrueba solo una vez
        try {
            insertarDatosDePrueba(); // Este paso puede ser opcional si ya has insertado datos
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Llamar a la consulta
        return ejecutarConsultaConRepositorio(model);
    }
    
    
}
