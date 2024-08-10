package com.softtek.WheelsWonder.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "alquileres")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private LocalDate fechaInicio;
    @Column(nullable = false)
    private LocalDate fechaFin;
    @Column(nullable = false)
    private double precio;
    @Column(nullable = false)
    private boolean activo;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_alquileres_usuarios"))
    private Usuario usuarioAlquiler;
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", nullable = false, foreignKey = @ForeignKey(name = "FK_alquileres_vehiculos"))
    private Vehiculo vehiculoAlquiler;
    @OneToOne(mappedBy = "alquilerPago",cascade = CascadeType.REMOVE,orphanRemoval = false,fetch = FetchType.LAZY)
    private Pago pagoAlquiler;
    @OneToOne(mappedBy = "alquilerValoracion",cascade = CascadeType.REMOVE,orphanRemoval = false,fetch = FetchType.LAZY)
    private Valoracion valoracionAlquiler;

}
