package com.app.dev83.sistemaventas.Repository;

import com.app.dev83.sistemaventas.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

     @Query(value =  "select * from productos where categoria_id= :idCategoria and activo=1",  nativeQuery = true)
     List<Producto> findAllByCategory(@Param("idCategoria") Integer idCategoria);

     @Query(value =  "select * from productos where stock>0", nativeQuery = true)
     List<Producto> findAllStockOn();
}
