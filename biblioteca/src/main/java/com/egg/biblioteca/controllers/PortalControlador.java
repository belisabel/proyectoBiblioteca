package com.egg.biblioteca.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.exceptions.MyException;
import com.egg.biblioteca.services.LibroServicio;
import com.egg.biblioteca.services.UsuarioServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    //private LibroServicio libroServicio;


    @GetMapping("/")  // Acá es donde realizamos el mapeo
    public String index() {
        return "index.html";   // Acá es que retornamos con el método. 
    }

//     Ser del tipo @PostMapping. Respondiendo a la url "/registro""

// Será del tipo String.

// Retornará la vista “index.html” en caso de que el registro sea de manera exitosa 
// (mostrando el mensaje correspondiente haciendo uso de nuestro ModelMap) o volverá a 
// nuestro formulario de registro indicando la excepción correspondiente para notificar al usuario (mostrando el mensaje correspondiente haciendo uso de nuestro ModelMap). 

// Presta atención a los nombres de las variables declaradas en el html.

// Recuerda crear una instancia de nuestra clase UsuarioServicio, para poder ingresar 
// al método “registrar” creado previamente. 


// En el método registrar de la clase UsuarioServicio, asegúrate de encriptar 
// la contraseña antes de guardarla en la base de datos. Para ello, utiliza el encriptador correspondiente dentro del servicio.

 //usuario.setPassword(new BCryptPasswordEncoder().encode(password));

 @PostMapping("/registro")
 public String registro(@RequestParam(required = false) String nombre, @RequestParam String email, @RequestParam String password,@RequestParam String password2, ModelMap modelo){

    try {
       
       
        usuarioServicio.registrar(nombre, email, password, password2);
        modelo.put("exito", "Usuario registrado correctamente");
        return "index.html";

    } catch (MyException ex) {

        modelo.put("error", ex.getMessage());
        modelo.put("nombre",nombre);
        modelo.put("email",email);

        return "registro.html"; // volvemos a cargar el formulario.
    }
     


     

 }


    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";

    }

    @GetMapping("/login")
    public String login(){
        return "login.html";

    }


//     En el controlador PortalControlador, deja previstos algunos métodos,
//      encargados de “retornar ciertas vistas” para registrarse como usuario, o para loguearse.

// Método registrar:

// Ser del tipo @GetMapping. Respondiendo a la url "/registrar"

// Será del tipo String

// Retornará la vista “registro.html”

// Método login:

// Ser del tipo @GetMapping. Respondiendo a la url "/login"

// Será del tipo String

// Retornará la vista  “login.html”

 
}