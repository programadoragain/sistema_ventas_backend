package com.app.dev83.sistemaventas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.dev83.sistemaventas.Entity.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {
}