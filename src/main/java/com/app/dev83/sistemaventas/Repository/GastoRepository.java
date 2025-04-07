package com.app.dev83.sistemaventas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.dev83.sistemaventas.Entity.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {

    @Query(value = "SELECT SUM(monto) FROM gastos WHERE DATE(fecha_pago) = :fecha", nativeQuery = true)
    Float totalGastos(@Param("fecha") String fecha);
}