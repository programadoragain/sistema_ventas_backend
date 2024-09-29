package com.app.dev83.sistemaventas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gastos")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String categoria;

    @NotNull
    private String descripcion;

    @NotNull
    float monto;

    @NotNull
    @Column(name = "fecha_pago")
    LocalDate fechaPago;

    @NotNull
    @Column(name = "forma_pago")
    String formaPago;
}
