package com.egg.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Usuario;
import com.egg.biblioteca.enumerations.Rol;
import com.egg.biblioteca.exceptions.MyException;
import com.egg.biblioteca.services.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

  @Autowired
  private UsuarioServicio usuarioServicio;

  @GetMapping("/dashboard")
  public String panelAdministrativo() {

    return "inicio.html"; // vista que contiene todas las funciones habilitadas: listar y modificar
  }

  @GetMapping("/usuarios")
  public String listar(ModelMap modelo) {

    List<Usuario> usuarios = usuarioServicio.listarUsuarios();
    modelo.addAttribute("usuarios", usuarios);
    return "usuarios_list.html"; // vista que contiene todos los usuarios de la plataforma de biblioteca
  }

 
  // cambio de rol de admin a user o viceversa, sólo el administrador puede
  // realizar cambios.
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @PostMapping("/editarRol2/{id}")
  public String actualizarRol2(@PathVariable String id, @RequestParam Rol rol,
      ModelMap modelo) throws MyException {
    usuarioServicio.actualizarRol(id, rol);
    modelo.put("exito", "Rol actualizado correctamente!");
    return "inicio.html";
  }

}
