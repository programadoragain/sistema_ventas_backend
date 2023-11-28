package com.app.dev83.sistemaventas.Dto;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenVentaDTO {
  private Integer id;
  private Float valorTotal;
  private String metodoPago;
  private String fechaCreacion;
  private String usuario;
  private List<DetalleVenta> detalleVenta;

  public static OrdenVentaDTO toOrdenVentaDTO(OrdenVenta ordenVenta) {
    return new OrdenVentaDTO(
            ordenVenta.getId(),
            ordenVenta.getValorTotal(),
            ordenVenta.getMetodoPago().name(),
            ordenVenta.getFechaCreacionConFormato(),
            ordenVenta.getUsuario().getNombre(),
            ordenVenta.getDetalleVenta().stream().unordered().collect(Collectors.toList()));
  }
}
