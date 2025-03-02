package com.egg.biblioteca.controllers;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.exceptions.MyException;
import com.egg.biblioteca.services.AutorServicio;
import com.egg.biblioteca.services.EditorialServicio;
import com.egg.biblioteca.services.LibroServicio;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") // localhost:8080/libro/registrar
    public String registrar() {
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo, 
            @RequestParam(required = false) Integer ejemplares, @RequestParam UUID idAutor,
              @RequestParam String idEditorial) {
          try {
              libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
  
  
          } catch (MyException ex) {
              
              return "libro_form.html"; // volvemos a cargar el formulario.
          }
          return "index.html";
      }

}
