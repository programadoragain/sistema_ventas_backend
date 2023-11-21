package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Dto.ProductoDTO;
import com.app.dev83.sistemaventas.Entity.Categoria;
import com.app.dev83.sistemaventas.Entity.Moneda;
import com.app.dev83.sistemaventas.Entity.Producto;
import com.app.dev83.sistemaventas.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public String registrar(Producto producto) {
        try {
            productoRepository.save(producto);
            return Constantes.SOLICITUD_EXITOSA;

        } catch (Exception ex) {
            ex.printStackTrace();
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

    @Override
    public List<ProductoDTO> listar() {
        List<Producto> productos= productoRepository.findAll();
        return productos.stream().map(ProductoDTO::toProductoDTO).collect(Collectors.toList());
                                   //(p -> ProductoDTO.toProductoDTO(p)).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> listarPorCategoria(String idCategoria) {
        List<Producto> productos= productoRepository.findAllByCategory(Integer.parseInt(idCategoria));
        return productos.stream().map(ProductoDTO::toProductoDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> listarEnStock() {
        List<Producto> productos= productoRepository.findAllStockOn();
        return productos.stream().map(ProductoDTO::toProductoDTO).collect(Collectors.toList());
    }

    @Override
    public Producto obtenerPorId(String id) {
        return productoRepository.findById(parseInt(id)).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public String actualizar(Producto producto) {
        try {
            Producto productoEnDB = productoRepository.findById(producto.getId()).orElseThrow(RuntimeException::new);

            Categoria categoria= new Categoria();
            categoria.setId(producto.getCategoria().getId());

            productoEnDB.setNombre(producto.getNombre());
            productoEnDB.setMarca(producto.getMarca());
            productoEnDB.setModelo(producto.getModelo());
            productoEnDB.setCodBarra(producto.getCodBarra());
            productoEnDB.setDescripcion(producto.getDescripcion());
            productoEnDB.setValor(producto.getValor());
            productoEnDB.setMoneda(producto.getMoneda());
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
    public String eliminar(String id) {
        try {
            productoRepository.deleteById(parseInt(id));
            return Constantes.SOLICITUD_EXITOSA;

        } catch(Exception ex) {
            ex.printStackTrace();
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

    @Override
    public void restarStock(Integer id, Integer cantidad) {
        Producto producto = productoRepository.findById(id).orElseThrow(RuntimeException::new);
        producto.setStock(producto.getStock() - cantidad);
        if (producto.getStock() < 1) producto.setActivo(false);
        productoRepository.save(producto);
    }

    @Override
    public void sumarStock(Integer id, Integer cantidad) {
        Producto producto = productoRepository.findById(id).orElseThrow(RuntimeException::new);
        producto.setStock(producto.getStock() + cantidad);
        productoRepository.save(producto);
    }

}
