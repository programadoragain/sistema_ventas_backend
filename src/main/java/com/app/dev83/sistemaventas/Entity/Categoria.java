package com.app.dev83.sistemaventas.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categorias")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categoria;
}
