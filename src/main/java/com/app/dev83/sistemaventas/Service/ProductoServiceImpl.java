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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.dev83.sistemaventas.Constants.Constantes.UPLOAD_DIRECTORY_PRODUCTS;
import static java.lang.Integer.parseInt;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto registrar(Producto producto) {
        try {
            producto.setMoneda(Moneda.PESO);
            producto.setActivo(producto.getStock() > 0);
            return productoRepository.save(producto);

        } catch (Exception ex) {
            return new Producto();
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
    public List<ProductoDTO> listarPorNombre(String nombre) {
        List<Producto> productos= productoRepository.findAllByName(nombre);
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
            productoEnDB.setActivo(producto.getStock() > 0);

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


    public long cantidadElementos() {
        return productoRepository.count();
    }

    @Override
    public String uploadPhoto(String id, MultipartFile file) throws IOException {
        // Crear el directorio de destino si no existe
        Path uploadPath = Paths.get(UPLOAD_DIRECTORY_PRODUCTS);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Guardar el archivo
        String fileName = id + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        //actualizar productos con el link a la imagen

        Producto producto= obtenerPorId(id);

        producto.setImagen(fileName);
        productoRepository.save(producto);

        return "Foto cargada exitosamente: " + fileName;
    }

}
