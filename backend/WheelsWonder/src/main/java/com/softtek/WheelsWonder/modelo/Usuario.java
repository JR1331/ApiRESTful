package com.softtek.WheelsWonder.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 9, nullable = false, unique = true)
    private String dni;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false, unique = true)
    private String correo;
    @Column(length = 9, nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String contrasena;
    @Column
    private String imagenURL;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioAlquiler",
            cascade = CascadeType.REMOVE,
            orphanRemoval = false,
            fetch = FetchType.LAZY
    )
    List<Alquiler> alquileres;
    @OneToMany(mappedBy = "usuarioVehiculo",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    List<Vehiculo> vehiculos;
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioValoracion",
            cascade = CascadeType.REMOVE,
            orphanRemoval = false,
            fetch = FetchType.LAZY
    )
    List<Valoracion> valoraciones;
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioPago",
            cascade = CascadeType.REMOVE,
            orphanRemoval = false,
            fetch = FetchType.LAZY
    )
    List<Pago> pagos;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
