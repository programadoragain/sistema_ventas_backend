package com.app.dev83.sistemaventas.Service;

import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Entity.Gasto;
import com.app.dev83.sistemaventas.Repository.GastoRepository;

@Service
public class GastoServiceImpl implements GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    @Override
    public Gasto registrar(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    @Override
    public List<Gasto> listar() {
        return gastoRepository.findAll();
    }

    @Override
    public Gasto obtenerPorId(String id) {
        return gastoRepository.findById(parseInt(id)).orElse(null);
    }

    @Override
    public Gasto actualizar(Gasto gasto) {
        if (gastoRepository.existsById(gasto.getId())) {
            return gastoRepository.save(gasto);
        }
        return null;
    }

    @Override
    public String eliminar(String id) {
        try {
            gastoRepository.deleteById(parseInt(id));
            return Constantes.SOLICITUD_EXITOSA;

        } catch(Exception ex) {
            return Constantes.OCURRIO_UN_ERROR;
        }
    }

    @Override
    public Float totalGastos(String fecha){
        return gastoRepository.totalGastos(fecha);
    }
    
}
