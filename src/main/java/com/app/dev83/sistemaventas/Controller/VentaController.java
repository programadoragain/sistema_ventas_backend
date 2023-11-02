package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import com.app.dev83.sistemaventas.Service.DetalleVentaService;
import com.app.dev83.sistemaventas.Service.OrdenVentaService;
import com.app.dev83.sistemaventas.Service.ProductoService;
import com.app.dev83.sistemaventas.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/venta")
public class VentaController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private OrdenVentaService ordenVentaService;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpSession httpSession;

    private OrdenVenta ordenVenta=  new OrdenVenta();
    private List<DetalleVenta> detalleVentas= new ArrayList<>();

}
