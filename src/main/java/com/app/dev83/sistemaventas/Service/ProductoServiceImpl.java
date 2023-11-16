package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Entity.Categoria;
import com.app.dev83.sistemaventas.Entity.Producto;
import com.app.dev83.sistemaventas.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public String registrarProducto(Producto producto) {
        try {
            productoRepository.save(producto);
            return Constantes.SOLICITUD_EXITOSA;

        } catch (Exception ex) {
            ex.printStackTrace();
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> listarProductosPorCategoria(String idCategoria) {
        return productoRepository.findAllByCategory(Integer.parseInt(idCategoria));
    }

    @Override
    public List<Producto> listarProductosEnStock() {
        return productoRepository.findAllStockOn();
    }

    @Override
    public Producto obtenerProductoPorId(String id) {
        return productoRepository.findById(parseInt(id)).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public String actualizarProducto(Producto producto) {
        try {
            Producto productoEnDB = productoRepository.findById(producto.getId()).orElseThrow(RuntimeException::new);

            Categoria categoria= new Categoria();
            categoria.setId(producto.getCategoria().getId());

            productoEnDB.setNombre(producto.getNombre());
            productoEnDB.setMarca(producto.getMarca());
            productoEnDB.setModelo(producto.getModelo());
            productoEnDB.setCodigoBarra(producto.getCodigoBarra());
            productoEnDB.setDescripcion(producto.getDescripcion());
            productoEnDB.setValorUsd(producto.getValorUsd());
            productoEnDB.setTipoDeCambio(producto.getTipoDeCambio());
            productoEnDB.setStock(producto.getStock());
            productoEnDB.setActivo(producto.isActivo());

            productoRepository.save(productoEnDB);
            return Constantes.SOLICITUD_EXITOSA;

        } catch(Exception ex) {
            ex.printStackTrace();
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

    @Override
    public String eliminarProducto(String id) {
        try {
            productoRepository.deleteById(parseInt(id));
            return Constantes.SOLICITUD_EXITOSA;

        } catch(Exception ex) {
            ex.printStackTrace();
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

}
