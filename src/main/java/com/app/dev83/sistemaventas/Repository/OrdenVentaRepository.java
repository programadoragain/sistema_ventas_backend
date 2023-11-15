package com.app.dev83.sistemaventas.Repository;

import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import com.app.dev83.sistemaventas.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenVentaRepository extends JpaRepository<OrdenVenta, Integer> {
    List<OrdenVenta> findAllByUsuario(Usuario usuario);
}
