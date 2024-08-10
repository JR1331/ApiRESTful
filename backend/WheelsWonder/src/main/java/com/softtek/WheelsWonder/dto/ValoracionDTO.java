package com.softtek.WheelsWonder.dto;

import com.softtek.WheelsWonder.modelo.Alquiler;
import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.modelo.Valoracion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValoracionDTO {
    private int id;

    private String descripcion;

    @NotNull
    private double puntuacion;

    @NotNull
    private AlquilerDTO alquilerValoracion;

    @NotNull
    private UsuarioDTO usuarioValoracion;

    public Valoracion castValoracion() {
        Valoracion v = new Valoracion();
        v.setId(this.id);
        v.setDescripcion(this.descripcion);
        v.setPuntuacion(this.puntuacion);
        v.setAlquilerValoracion(this.alquilerValoracion.castAlquiler());
        v.setUsuarioValoracion(this.usuarioValoracion.castUsuario());
        return v;
    }

    public ValoracionDTO castValoracionADto(Valoracion v) {
        this.id = v.getId();
        this.descripcion = v.getDescripcion();
        this.puntuacion = v.getPuntuacion();
        this.alquilerValoracion = new AlquilerDTO().castAlquilerADto(v.getAlquilerValoracion());
        this.usuarioValoracion = new UsuarioDTO().castUsuarioADto(v.getUsuarioValoracion());
        return this;
    }
}
