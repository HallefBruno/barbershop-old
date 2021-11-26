package com.manager.barbershop.repository;

import com.manager.barbershop.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Usuario, Long>{
    
}
