package com.softtek.WheelsWonder.dto;

import com.softtek.WheelsWonder.modelo.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlquilerDTO {
    private int id;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    @NotNull
    private Double precio;

    @NotNull
    private boolean activo;

    @NotNull
    private UsuarioDTO usuarioAlquiler;

    @NotNull
    private VehiculoDTO vehiculoAlquiler;

    private PagoDTO pagoAlquiler;
    private ValoracionDTO valoracionAlquiler;

    public Alquiler castAlquiler() {
        Alquiler a = new Alquiler();
        a.setId(this.id);
        a.setFechaInicio(this.fechaInicio);
        a.setFechaFin(this.fechaFin);
        a.setPrecio(this.precio);
        a.setActivo(this.activo);
        a.setUsuarioAlquiler(this.usuarioAlquiler.castUsuario());
        a.setVehiculoAlquiler(this.vehiculoAlquiler.castVehiculo());
        a.setPagoAlquiler(this.pagoAlquiler != null ? this.pagoAlquiler.castPago() : null);
        a.setValoracionAlquiler(this.valoracionAlquiler != null ? this.valoracionAlquiler.castValoracion() : null);
        return a;
    }

    public AlquilerDTO castAlquilerADto(Alquiler a) {
        this.id = a.getId();
        this.fechaInicio = a.getFechaInicio();
        this.fechaFin = a.getFechaFin();
        this.precio = a.getPrecio();
        this.activo = a.isActivo();
        this.usuarioAlquiler = new UsuarioDTO().castUsuarioADto(a.getUsuarioAlquiler());
        this.vehiculoAlquiler = new VehiculoDTO().castVehiculoADto(a.getVehiculoAlquiler());
        this.pagoAlquiler = a.getPagoAlquiler() != null ? new PagoDTO().castPagoADto(a.getPagoAlquiler()) : null;
        this.valoracionAlquiler = a.getValoracionAlquiler() != null ? new ValoracionDTO().castValoracionADto(a.getValoracionAlquiler()) : null;
        return this;
    }
}
