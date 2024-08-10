package com.softtek.WheelsWonder.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String dni;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasena;
}