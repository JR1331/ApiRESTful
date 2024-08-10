package com.softtek.WheelsWonder.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20, unique = true, nullable = false)
    private String matricula;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Column
    private String descripcion;
    @Column(nullable = false)
    private String imagenURL;
    @Column(nullable = false)
    private String localizacion;
    @Column(nullable = false)
    private double precio;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_vehiculos_usuarios"))
    private Usuario usuarioVehiculo;
    @JsonIgnore
    @OneToMany(mappedBy = "vehiculoAlquiler",
            cascade = CascadeType.REMOVE,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    List<Alquiler> alquileres;
}
