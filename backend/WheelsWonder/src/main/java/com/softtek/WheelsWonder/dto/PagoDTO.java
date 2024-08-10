package com.softtek.WheelsWonder.dto;

import com.softtek.WheelsWonder.modelo.Alquiler;
import com.softtek.WheelsWonder.modelo.Pago;
import com.softtek.WheelsWonder.modelo.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagoDTO {
    private int id;

    @NotEmpty
    private String metodo;

    @NotNull
    private double cantidad;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private AlquilerDTO alquilerPago;

    @NotNull
    private UsuarioDTO usuarioPago;

    public Pago castPago() {
        Pago p = new Pago();
        p.setId(this.id);
        p.setMetodo(this.metodo);
        p.setCantidad(this.cantidad);
        p.setFecha(this.fecha);
        p.setAlquilerPago(this.alquilerPago.castAlquiler());
        p.setUsuarioPago(this.usuarioPago.castUsuario());
        return p;
    }

    public PagoDTO castPagoADto(Pago p) {
        this.id = p.getId();
        this.metodo = p.getMetodo();
        this.cantidad = p.getCantidad();
        this.fecha = p.getFecha();
        this.alquilerPago = new AlquilerDTO().castAlquilerADto(p.getAlquilerPago());
        this.usuarioPago = new UsuarioDTO().castUsuarioADto(p.getUsuarioPago());
        return this;
    }
}
