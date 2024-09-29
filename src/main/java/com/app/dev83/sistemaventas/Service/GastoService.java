package com.app.dev83.sistemaventas.Service;

import java.util.List;

import com.app.dev83.sistemaventas.Entity.Gasto;

public interface GastoService {
    List<Gasto> listar();
    Gasto obtenerPorId(String id);
    Gasto registrar(Gasto gasto);
    Gasto actualizar(Gasto gasto);
    String eliminar(String id);
}
