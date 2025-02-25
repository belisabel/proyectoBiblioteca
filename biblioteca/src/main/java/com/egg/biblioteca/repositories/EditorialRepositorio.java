package com.egg.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egg.biblioteca.entities.Editorial;



@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

    
} 
