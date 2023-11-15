package com.app.dev83.sistemaventas.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="usuarios")
@Data
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Rol role;
    private String status;
}
