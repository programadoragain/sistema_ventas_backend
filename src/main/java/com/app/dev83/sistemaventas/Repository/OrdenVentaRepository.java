package com.app.dev83.sistemaventas.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import com.app.dev83.sistemaventas.Entity.Usuario;

@Repository
public interface OrdenVentaRepository extends JpaRepository<OrdenVenta, Integer> {
    List<OrdenVenta> findAllByUsuario(Usuario usuario);

    @Query(value =  "select * from orden_ventas ORDER BY id DESC", nativeQuery = true)
    List<OrdenVenta> findAllOrderDes();

    @Query(value =  "select * from orden_ventas where DATE(fecha_creacion)= :fecha", nativeQuery = true)
    List<OrdenVenta> listadoVentasPorFecha(@Param("fecha") String fecha);

    @Query(value =  "select sum(valor_total) from orden_ventas where DATE(fecha_creacion)= :fecha", nativeQuery = true)
    Float totalOrdenVentasDiario(@Param("fecha") String fecha);

    @Query(value = "select sum(valor_total) FROM orden_ventas WHERE MONTH(fecha_creacion) = :mes AND YEAR(fecha_creacion) = :anio", nativeQuery = true)
    Float totalPorMesyAnio(@Param("mes") int mes, @Param("anio") int anio);

    @Query(value = "SELECT SUM(valor_total) FROM orden_ventas WHERE DATE(fecha_creacion) = :fecha", nativeQuery = true)
    Float obtenerTotalVentasPorFecha(LocalDate fecha);

    @Query(value = "SELECT SUM(valor_total) FROM orden_ventas WHERE DATE(fecha_creacion) = :fecha AND metodo_pago = :metodoPago", nativeQuery = true)
    Float totalPorFechayTipoPago(@Param("fecha") String fecha, @Param("metodoPago") int metodoPago);

    @Query(value =  "SELECT SUM(dv.cantidad) AS total_productos FROM orden_ventas ov JOIN orden_ventas_detalle_venta ovdv ON ov.id = ovdv.orden_venta_id " +
                    "JOIN detalle_ventas dv ON ovdv.detalle_venta_id = dv.id WHERE DATE(ov.fecha_creacion) = :fecha", nativeQuery = true)
    Integer itemsVendidosPorFecha(@Param("fecha") String fecha);

}
