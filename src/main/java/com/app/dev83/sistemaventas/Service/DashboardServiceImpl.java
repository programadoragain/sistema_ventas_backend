package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadisticaServiceImpl implements EstadisticaService{

    @Autowired
    private OrdenVentaService ordenVentaService;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public String cantidadVentaDiaria() {
        return "";
    }
}
