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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrdenVentaServiceImpl implements OrdenVentaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenVentaRepository ordenVentaRepository;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    @Transactional
    public String registrarVenta(Map<String, Object> requestMap) {

        if (validarVenta(requestMap)) {
            OrdenVenta venta = new OrdenVenta();

            List<DetalleVenta> detalles= new ArrayList<>();
            mapearDetallesVenta(detalles, requestMap.get("detallesVenta"));
            venta.setDetalleVenta(detalles);

            Usuario vendedor= usuarioService.usuarioActual();
            venta.setUsuario(vendedor);

            venta.setMetodoPago((MetodoPago) requestMap.get("metodoPago"));
            venta.setValorTotal(Float.parseFloat((String) requestMap.get("total")));
            venta.setFechaCreacion(new Date());

            ordenVentaRepository.save(venta);

            return "Se registro la venta correctamente";
        }
        else
            return "No se pudo registrar la venta, deben completarse todos los campos";

    }

    private void mapearDetallesVenta(List<DetalleVenta> detalles, String detallesVenta) {
    }

    @Override
    public List<OrdenVenta> listarVentas() {
        List<OrdenVenta> ventas= new ArrayList<>();

        if (jwtFilter.isAdmin())
            ventas= ordenVentaRepository.findAll();
        else
            ventas= ordenVentaRepository.findAllByUsuario(usuarioService.usuarioActual());

        return ventas;
    }

    @Override
    public void eliminarVenta(Integer id) {
        if (ordenVentaRepository.findById(id).isPresent())
            ordenVentaRepository.deleteById(id);
    }

    private boolean validarVenta(Map<String, Object> requestMap) {
        return ((requestMap.containsKey("total")) &&
                (requestMap.containsKey("detalleVentas")) &&
                (requestMap.containsKey("metodoPago")));
    }
}
