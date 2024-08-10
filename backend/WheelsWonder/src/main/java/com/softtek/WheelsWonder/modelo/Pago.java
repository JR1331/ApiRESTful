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
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String metodo;
    @Column(nullable = false)
    private double cantidad;
    @Column(nullable = false)
    private LocalDate fecha;

    @JoinColumn(name="id_alquiler")
    @OneToOne(fetch = FetchType.LAZY)
    private Alquiler alquilerPago;
    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_pagos_usuarios"))
    private Usuario usuarioPago;


}
