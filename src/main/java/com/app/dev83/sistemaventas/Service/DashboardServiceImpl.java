package com.app.dev83.sistemaventas.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dev83.sistemaventas.Repository.OrdenVentaRepository;
import com.app.dev83.sistemaventas.Repository.UsuarioRepository;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private OrdenVentaRepository ordenVentaRepository;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Map<String, String> cardVentas() {
        Map<String,String> data= new HashMap<>();

        Float ventaDiaAcutal= ordenVentaRepository.totalOrdenVentasDiario(LocalDate.now().toString());
        Float ventaDiaAyer= ordenVentaRepository.totalOrdenVentasDiario(LocalDate.now().minusDays(1).toString());

        if (ventaDiaAcutal == null) ventaDiaAcutal= 0.0F;
        if (ventaDiaAyer == null) ventaDiaAyer= 0.0F;

        String arrow= (ventaDiaAcutal > ventaDiaAyer) ? "up" : "down";
        float percentage= ((ventaDiaAcutal * 100) / ventaDiaAyer);

        data.put("title", "VENTAS");
        data.put("value", ventaDiaAcutal.toString());
        data.put("arrow", arrow);
        if (Float.toString(percentage).equals("Infinity")) {
            data.put("percentage", "--");
        } else {
            data.put("percentage", Float.toString(percentage));
        }

        return data;
    }

    @Override
    public Map<String, String> cardProductos() {
        Map<String,String> data= new HashMap<>();
        data.put("title", "PRODUCTOS");
        data.put("value", Long.toString(productoService.cantidadElementos()));
        data.put("percentage", "10%");
        data.put("arrow", "up");

        return data;
    }

    @Override
    public Map<String, String> cardGastos() {
        Map<String,String> data= new HashMap<>();
        data.put("title", "GASTOS");
        data.put("value", "25.900");
        data.put("percentage", "5%");
        data.put("arrow", "up");

        return data;
    }

    @Override
    public Map<String, String> cardTotal() {
        Map<String,String> data= new HashMap<>();
        data.put("title", "TOTAL");

        int mes= LocalDate.now().getMonthValue();
        int anio= LocalDate.now().getYear();

        data.put("value", ordenVentaRepository.totalPorMesyAnio(mes, anio).toString());
        data.put("percentage", "");
        data.put("arrow", "up");

        return data;
    }

    @Override
    public List<Map<String, String>> grafico() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicio = fechaActual.minusDays(6);
        
        List<Map<String, String>> totalesDiariosPorSemana = new ArrayList<>();
        
        for (int i = 0; i < 7; i++) {
            LocalDate fecha = fechaInicio.plusDays(i);
            Float total = ordenVentaRepository.obtenerTotalVentasPorFecha(fecha);
            
            Map<String, String> datosDia = new HashMap<>();
            // Ejemplo: "Lunes 15"
            datosDia.put("name", fecha.format(DateTimeFormatter.ofPattern("EEEE dd", new Locale("es", "ES"))));
            datosDia.put("value", total != null ? String.valueOf(total) : "0");
            
            totalesDiariosPorSemana.add(datosDia);
        }
        
        return totalesDiariosPorSemana;
    }



}
