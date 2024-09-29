package com.app.dev83.sistemaventas.Repository;

import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import com.app.dev83.sistemaventas.Entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdenVentaRepository extends JpaRepository<OrdenVenta, Integer> {
    List<OrdenVenta> findAllByUsuario(Usuario usuario);

    @Query(value =  "select * from orden_ventas ORDER BY DATE(fecha_creacion) DESC", nativeQuery = true)
    List<OrdenVenta> findAllOrderByDate();

    @Query(value =  "select sum(valor_total) from orden_ventas where DATE(fecha_creacion)= :fecha", nativeQuery = true)
    Float totalOrdenVentasDiario(@Param("fecha") String fecha);

    @Query(value = "select sum(valor_total) FROM orden_ventas WHERE MONTH(fecha_creacion) = :mes AND YEAR(fecha_creacion) = :anio", nativeQuery = true)
    Float totalPorMesyAnio(@Param("mes") int mes, @Param("anio") int anio);

    @Query(value = "SELECT SUM(valor_total) FROM orden_ventas WHERE DATE(fecha_creacion) = :fecha", nativeQuery = true)
    Float obtenerTotalVentasPorFecha(LocalDate fecha);




}
