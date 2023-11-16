package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import com.app.dev83.sistemaventas.Repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService{

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public void registrarDetalleVenta(List<DetalleVenta> detalles, Object requestMap) {

    }
}
