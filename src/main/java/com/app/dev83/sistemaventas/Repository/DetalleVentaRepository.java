package com.app.dev83.sistemaventas.Repository;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
}
