package com.manager.barbershop.repository;

import com.manager.barbershop.model.ClienteSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteSistemaRepository extends JpaRepository<ClienteSistema, Long> {
    
}
