package com.proyecto.proyectojunior.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.util.Locale;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
    private String nombreUsuario;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String contrasena;
    // Guarda nombreUsuario y email siempre en minúsculas antes de insertar o actualizar,
    // para que la restricción unique también impida duplicados por mayúsculas
    @PrePersist
    @PreUpdate
    private void normalizar() {
        if (nombreUsuario != null) {
            nombreUsuario = nombreUsuario.toLowerCase(Locale.ROOT);
        }
        if (email != null) {
            email = email.toLowerCase(Locale.ROOT);
        }
    }
}