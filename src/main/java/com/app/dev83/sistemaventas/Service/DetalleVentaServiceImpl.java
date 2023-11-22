package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Dto.ProductoDTO;
import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import com.app.dev83.sistemaventas.Entity.Producto;
import com.app.dev83.sistemaventas.Repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoService productoService;

    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public boolean registrar(List<DetalleVenta> detalles, Object request, String valorTotal) throws RuntimeException {

        List items = (List) request;
        int tamano = items.size();
        Float total = 0F;

        for (var item : items) {
            Map<String, String> requestMap = (Map<String, String>) item;
            Producto producto = productoService.obtenerPorId(requestMap.get("producto"));
            Integer cantidad = Integer.parseInt(requestMap.get("cantidad"));

            if ((producto != null) && (producto.getStock() - cantidad >= 0)) {
                DetalleVenta renglon = new DetalleVenta();
                renglon.setProducto(producto);
                renglon.setCantidad(cantidad);
                renglon.setValor(Float.parseFloat(requestMap.get("total")));
                detalles.add(detalleVentaRepository.save(renglon));
                productoService.restarStock(producto.getId(), cantidad);
                tamano--;
                total += Float.parseFloat(requestMap.get("total"));
            }
        }

        /**
         * Si el tamano de la lista es 0, es porque se registraron todos los detalles de venta,
         * Si el total es igual al valorTotal, es porque coinciden con el total de la venta en OrdenVenta,
         * de lo contrario se hace un rollback para que los registros no se almacenen.
         */
        if ( (tamano == 0) && (total == Float.parseFloat(valorTotal)) ) {
            return true;
        } else
            throw new RuntimeException();
    }

}
