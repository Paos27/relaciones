package com.T3_relaciones.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Data
public class Libro {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	
	
}
