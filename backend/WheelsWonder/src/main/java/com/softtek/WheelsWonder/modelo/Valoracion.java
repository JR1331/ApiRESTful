package com.softtek.WheelsWonder.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "valoraciones")
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String descripcion;
    @Column(nullable = false)
    private double puntuacion;

    @JoinColumn(name="id_alquiler")
    @OneToOne(fetch = FetchType.LAZY)
    private Alquiler alquilerValoracion;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_valoraciones_usuarios"))
    private Usuario usuarioValoracion;
}
