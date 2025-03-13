package com.egg.biblioteca.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egg.biblioteca.entities.Imagen;

public interface ImagenRepositorio  extends JpaRepository<Imagen, UUID>{
    
}
