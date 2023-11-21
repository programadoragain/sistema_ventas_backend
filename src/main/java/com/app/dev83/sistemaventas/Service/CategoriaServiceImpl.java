package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Dto.CategoriaDTO;
import com.app.dev83.sistemaventas.Entity.Categoria;
import com.app.dev83.sistemaventas.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public String registrar(Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return Constantes.SOLICITUD_EXITOSA;

        } catch (Exception ex) {
            ex.printStackTrace();
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

    @Override
    public String actualizar(Categoria categoria) {
        try {
            Categoria categoriaEnDB= categoriaRepository.findById(categoria.getId()).orElseThrow(RuntimeException::new);

            categoriaEnDB.setNombre(categoria.getNombre());

            categoriaRepository.save(categoriaEnDB);
            return Constantes.SOLICITUD_EXITOSA;

        } catch(Exception ex) {
            ex.printStackTrace();
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

    @Override
    public String eliminar(String id) {
        try {
            categoriaRepository.deleteById(parseInt(id));
            return Constantes.SOLICITUD_EXITOSA;

        } catch(Exception ex) {
            ex.printStackTrace();
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

    @Override
    public List<CategoriaDTO> listar() {
        List<Categoria> categorias= categoriaRepository.findAll();
        return categorias.stream().map(CategoriaDTO::toCategoriaDTO).collect(Collectors.toList());
    }
}
