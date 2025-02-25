package com.egg.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.biblioteca.entities.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,Long> {


@Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
 public Libro buscarPorTitulo(@Param("titulo") String titulo);

 @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :autorBuscado")
 public List<Libro> buscarPorAutor(@Param("autor") String autorBuscado);
 

    
} 
