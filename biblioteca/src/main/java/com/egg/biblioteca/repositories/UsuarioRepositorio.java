package com.egg.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.biblioteca.entities.Libro;
import com.egg.biblioteca.entities.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {


//     @Query("SELECT u FROM Usuario u WHERE u.email = :email")
//  public Usuario buscarPorEmail(@Param("email") String email);


    Usuario findByEmail(String email);
}
    
    // Crea un método llamado buscarPorEmail, 
    // que recibirá un parámetro de tipo String (el email del usuario) y devolverá el usuario correspondiente.

