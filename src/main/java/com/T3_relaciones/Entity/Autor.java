package com.T3_relaciones.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Data

public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    // Relación Uno a Muchos con la entidad Libro
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id") // La columna 'autor_id' en la tabla Libro
    private List<Libro> libros = new ArrayList<>();

    // Relación Muchos a Muchos con la entidad Categoria
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "autor_categoria",  // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id"),  // Clave foránea a 'Autor'
        inverseJoinColumns = @JoinColumn(name = "categoria_id")  // Clave foránea a 'Categoria'
    )
    private Set<Categoria> categorias = new HashSet<>();  // Relación con Categoria (no con Autor)
}
