package com.app.dev83.sistemaventas.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dev83.sistemaventas.Dto.OrdenVentaDTO;
import com.app.dev83.sistemaventas.Entity.MetodoPago;
import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import com.app.dev83.sistemaventas.Repository.GastoRepository;
import com.app.dev83.sistemaventas.Repository.OrdenVentaRepository;
import com.app.dev83.sistemaventas.Repository.ProductoRepository;
import com.app.dev83.sistemaventas.Repository.UsuarioRepository;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private OrdenVentaRepository ordenVentaRepository;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private GastoService gastoService;

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Map<String, String> cardVentas() {
        Map<String, String> data = new HashMap<>();

        Float ventaDiaAcutal = ordenVentaRepository.totalOrdenVentasDiario(LocalDate.now().toString());
        Float ventaDiaAyer = ordenVentaRepository.totalOrdenVentasDiario(LocalDate.now().minusDays(1).toString());

        data.put("title", "VENTAS");
        data.put("value", (ventaDiaAcutal != null) ? ventaDiaAcutal.toString() : "0");

        if ((ventaDiaAcutal != null) && (ventaDiaAyer != null)) {
            String arrow = (ventaDiaAcutal > ventaDiaAyer) ? "up" : "down";
            int percentage = (int) ((ventaDiaAcutal * 100) / ventaDiaAyer);

            data.put("arrow", arrow);
            data.put("percentage", percentage + "%");
        } else {
            //data.put("arrow", "");
            data.put("percentage", "");
        }

        return data;
    }

    @Override
    public Map<String, String> cardProductos(String fecha) {
        Map<String, String> data = new HashMap<>();
        Integer x = ordenVentaRepository.itemsVendidosPorFecha(fecha);
        data.put("title", "PRODUCTOS");
        data.put("value", (x == null ? "0" : Integer.toString(x)));
        //data.put("percentage", "");
        //data.put("arrow", "");

        return data;
    }

    @Override
    public Map<String, String> cardGastos() {
        Map<String, String> data = new HashMap<>();

        Float gastoDiaAcutal = gastoService.totalGastos(LocalDate.now().toString());
        Float gastoDiaAyer = gastoService.totalGastos(LocalDate.now().minusDays(1).toString());

        data.put("title", "GASTOS");
        data.put("value", (gastoDiaAcutal != null) ? gastoDiaAcutal.toString() : "0");

        if ((gastoDiaAcutal != null) && (gastoDiaAyer != null)) {
            String arrow = (gastoDiaAcutal > gastoDiaAyer) ? "up" : "down";
            int percentage = (int) ((gastoDiaAcutal * 100) / gastoDiaAyer);

            data.put("arrow", arrow);
            data.put("percentage", percentage + "%");
        } else {
            //data.put("arrow", "");
            data.put("percentage", "");
        }

        return data;
    }

    @Override
    public Map<String, String> cardTotal() {
        Map<String, String> data = new HashMap<>();
        data.put("title", "TOTAL");

        int mes = LocalDate.now().getMonthValue();
        int anio = LocalDate.now().getYear();

        data.put("value", ordenVentaRepository.totalPorMesyAnio(mes, anio).toString());
        data.put("percentage", "");
        //data.put("arrow", "");

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

    @Override
    public List<Map<String, Object>> graficoTorta1() {

        List<Map<String, Object>> dataList = new ArrayList<>();

        String fecha = LocalDate.now().toString();

        Float total = ordenVentaRepository.totalOrdenVentasDiario(fecha);
        Float totalEfectivo = ordenVentaRepository.totalPorFechayTipoPago(fecha, MetodoPago.EFECTIVO.ordinal());
        Float totalDebito = ordenVentaRepository.totalPorFechayTipoPago(fecha, MetodoPago.DEBITO.ordinal());
        Float totalCredito = ordenVentaRepository.totalPorFechayTipoPago(fecha, MetodoPago.CREDITO.ordinal());
        Float totalMercadoPago = ordenVentaRepository.totalPorFechayTipoPago(fecha, MetodoPago.MERCADOPAGO.ordinal());

        if (total != null) {
            if (totalEfectivo != null) {
                Map<String, Object> efectivoMap = new HashMap<>();
                efectivoMap.put("value", calcularPorcentaje(totalEfectivo, total));
                efectivoMap.put("total", totalEfectivo);
                efectivoMap.put("label", "Efectivo");
                dataList.add(efectivoMap);
            }
            if (totalDebito != null) {
                Map<String, Object> debitoMap = new HashMap<>();
                debitoMap.put("value", calcularPorcentaje(totalDebito, total));
                debitoMap.put("total", totalDebito);
                debitoMap.put("label", "Debito");
                dataList.add(debitoMap);
            }
            if (totalCredito != null) {
                Map<String, Object> creditoMap = new HashMap<>();
                creditoMap.put("value", calcularPorcentaje(totalCredito, total));
                creditoMap.put("total", totalCredito);
                creditoMap.put("label", "Credito");
                dataList.add(creditoMap);
            }
            if (totalMercadoPago != null) {
                Map<String, Object> mercadoPagoMap = new HashMap<>();
                mercadoPagoMap.put("value", calcularPorcentaje(totalMercadoPago, total));
                mercadoPagoMap.put("total", totalMercadoPago);
                mercadoPagoMap.put("label", "M. Pago");
                dataList.add(mercadoPagoMap);
            }

        };

        return dataList;
    }

    private float calcularPorcentaje(Float valor, Float total) {
        return Math.round(((valor * 100) / total) * 10.0) / 10.0f;
    }

    public List<OrdenVentaDTO> listarVentaPorDia(String fecha) {
        List<OrdenVenta> listaVentas= ordenVentaRepository.listadoVentasPorFecha(fecha);
        List<OrdenVentaDTO> listaVentasDTO= listaVentas.stream().map(lv ->  OrdenVentaDTO.toOrdenVentaDTO(lv)).collect(Collectors.toList());

        return listaVentasDTO;
    }
}
