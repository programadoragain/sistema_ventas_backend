package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import com.app.dev83.sistemaventas.Entity.MetodoPago;
import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import com.app.dev83.sistemaventas.Entity.Usuario;
import com.app.dev83.sistemaventas.Jwt.JwtFilter;
import com.app.dev83.sistemaventas.Repository.OrdenVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrdenVentaServiceImpl implements OrdenVentaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenVentaRepository ordenVentaRepository;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    @Transactional
    public String registrar(Map<String, Object> requestMap) {

        if (validarVenta(requestMap)) {
            OrdenVenta venta = new OrdenVenta();

            List<DetalleVenta> detalles= new ArrayList<>();

            if (detalleVentaService.registrar(detalles, requestMap.get("detalleVenta"), (String) requestMap.get("valorTotal"))) {
                venta.setDetalleVenta(detalles);

                Usuario vendedor = usuarioService.usuarioActual();
                venta.setUsuario(vendedor);

                venta.setMetodoPago(MetodoPago.valueOf((String) requestMap.get("metodoPago")));
                venta.setValorTotal(Float.parseFloat((String) requestMap.get("valorTotal")));
                venta.setFechaCreacion(LocalDateTime.now());

                ordenVentaRepository.save(venta);

                return "Se registró la venta correctamente.";
            }
        }
        return "No se pudo registrar la venta";
    }

    @Override
    public List<OrdenVenta> listar() {
        List<OrdenVenta> ventas= new ArrayList<>();

        if (jwtFilter.isAdmin())
            ventas= ordenVentaRepository.findAll();
        else
            ventas= ordenVentaRepository.findAllByUsuario(usuarioService.usuarioActual());

        return ventas;
    }

    @Override
    public void eliminar(Integer id) {
        if (ordenVentaRepository.findById(id).isPresent())
            ordenVentaRepository.deleteById(id);
    }

    private boolean validarVenta(Map<String, Object> requestMap) {
        return ( (requestMap.containsKey("valorTotal")) &&
                 (requestMap.containsKey("detalleVenta")) &&
                 (requestMap.containsKey("metodoPago")) );
    }
}
