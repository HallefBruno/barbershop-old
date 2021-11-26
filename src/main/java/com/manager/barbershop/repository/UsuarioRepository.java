
package com.manager.barbershop.repository;

import com.manager.barbershop.model.Usuario;
import com.manager.barbershop.repository.querys.usuario.UsuarioRepositoryCustom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom {
    public Optional<Usuario> findByEmail(String email);
}
