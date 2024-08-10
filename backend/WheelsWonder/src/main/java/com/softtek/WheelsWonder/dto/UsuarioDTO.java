package com.softtek.WheelsWonder.dto;

import com.softtek.WheelsWonder.modelo.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {

    private int id;

    @NotNull
    @Size(min = 9, max = 9)
    private String dni;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String correo;

    @NotEmpty
    @Size(min = 9, max = 9)
    private String telefono;

    @NotEmpty
    private String contrasena;

    private String imagenURL;


    public Usuario castUsuario (){
        Usuario u = new Usuario();
        u.setId(id);
        u.setDni(dni);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setCorreo(correo);
        u.setTelefono(telefono);
        u.setContrasena(contrasena);
        u.setImagenURL(imagenURL);
        return u;
    }

    public UsuarioDTO castUsuarioADto(Usuario u){
        id = u.getId();
        dni = u.getDni();
        nombre = u.getNombre();
        apellido = u.getApellido();
        correo = u.getCorreo();
        telefono = u.getTelefono();
        contrasena = u.getContrasena();
        imagenURL = u.getImagenURL();
        return this;
    }
}
