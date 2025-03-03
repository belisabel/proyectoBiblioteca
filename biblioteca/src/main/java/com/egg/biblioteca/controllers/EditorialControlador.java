package com.egg.biblioteca.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.exceptions.MyException;
import com.egg.biblioteca.services.EditorialServicio;

@Controller
@RequestMapping("/editorial") 
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;
     @GetMapping("/registrar")
    public String registrar(){


        return "editorial_form.html";
    }

     @PostMapping("/registro") // localhost:8080/editorial/registro
    public String registro(@RequestParam String nombre,ModelMap modelo) {
        try {
            editorialServicio.crearEditorial(nombre);  // llamo a mi servicio para persistir 
            modelo.put("exito", "La editorial fue cargada exitosamente");        
        } catch (MyException ex) {     
            modelo.put("error",ex.getMessage());     
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }        
        return "index.html";
    }


    
}
