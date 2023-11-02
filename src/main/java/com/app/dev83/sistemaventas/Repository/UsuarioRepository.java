package com.app.dev83.sistemaventas.Repository;

import com.app.dev83.sistemaventas.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(@Param("email") String email);
}
