package com.softtek.WheelsWonder.dto;

import com.softtek.WheelsWonder.modelo.Tipo;
import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.modelo.Vehiculo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoDTO {
    private int id;

    @NotEmpty
    private String matricula;

    @NotEmpty
    private String marca;

    @NotEmpty
    private Tipo tipo;

    private String descripcion;

    @NotEmpty
    private String imagenURL;

    @NotEmpty
    private String localizacion;

    @NotNull
    private double precio;

    @NotNull
    private UsuarioDTO usuarioVehiculo;

    public Vehiculo castVehiculo() {
        Vehiculo v = new Vehiculo();
        v.setId(this.id);
        v.setMatricula(this.matricula);
        v.setMarca(this.marca);
        v.setTipo(this.tipo);
        v.setDescripcion(this.descripcion);
        v.setImagenURL(this.imagenURL);
        v.setLocalizacion(this.localizacion);
        v.setPrecio(this.precio);
        v.setUsuarioVehiculo(this.usuarioVehiculo.castUsuario());
        return v;
    }

    public VehiculoDTO castVehiculoADto(Vehiculo v) {
        this.id = v.getId();
        this.matricula = v.getMatricula();
        this.marca = v.getMarca();
        this.tipo = v.getTipo();
        this.descripcion = v.getDescripcion();
        this.imagenURL = v.getImagenURL();
        this.localizacion = v.getLocalizacion();
        this.precio = v.getPrecio();
        this.usuarioVehiculo = new UsuarioDTO().castUsuarioADto(v.getUsuarioVehiculo());
        return this;
    }
}
