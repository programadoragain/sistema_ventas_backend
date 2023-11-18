package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import com.app.dev83.sistemaventas.Entity.Producto;
import com.app.dev83.sistemaventas.Repository.DetalleVentaRepository;
import com.app.dev83.sistemaventas.Repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    @Override
    @Transactional
    public void registrarDetalleVenta(List<DetalleVenta> detalles, Object request) {

        List items = (List) request;

        for (var item : items) {
            Map<String, String> requestMap = (Map<String, String>) item;
            Producto producto = productoService.obtenerProductoPorId(requestMap.get("producto"));
            Integer cantidad = Integer.parseInt(requestMap.get("cantidad"));

            if ((producto != null) && (producto.getStock() - cantidad >= 0)) {
                DetalleVenta renglon = new DetalleVenta();
                renglon.setProducto(producto);
                renglon.setCantidad(cantidad);
                renglon.setValor(producto.getValorUsd() * cantidad);
                detalles.add(detalleVentaRepository.save(renglon));
                productoService.restarStock(producto.getId(), cantidad);
            }
        }
    }

}
