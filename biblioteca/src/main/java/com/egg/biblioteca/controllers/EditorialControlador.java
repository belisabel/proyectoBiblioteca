package com.egg.biblioteca.controllers;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.exceptions.MyException;
import com.egg.biblioteca.services.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") // lanza el formulario de registro de editoriales
    public String registrar() {

        return "editorial_form.html";
    }

    @PostMapping("/registro") // localhost:8080/editorial/registro
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {
            editorialServicio.crearEditorial(nombre); // llamo a mi servicio para persistir
            modelo.put("exito", "La editorial fue cargada exitosamente");
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }
        return "inicio.html";
    }

    @GetMapping("/lista")  // obtiene la lista de editoriales
    public String listar(ModelMap modelo) {

        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "editorial_list.html";
    }

    @GetMapping("/modificar/{id}") // modifica editoriales según id , método get
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", editorialServicio.getOne(id));

        return "editorial_modificar.html";
    }

    @PostMapping("/modificar/{id}") // modifica la editorial , método post
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {
        try {
            editorialServicio.modificarEditorial(nombre, id);

            return "redirect:../lista";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "editorial_modificar.html";
        }
    }

    @PostMapping("/eliminar/{id}") // elimina editorial
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        try {
            editorialServicio.eliminar( id);

            modelo.put("exito", "La editorial fue eliminada exitosamente, así mismo todos los libros que tienen esa editorial");

            return  "inicio.html";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "editorial_list.html";
        }
    }

}
