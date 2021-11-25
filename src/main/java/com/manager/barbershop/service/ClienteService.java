package com.manager.barbershop.service;

import com.manager.barbershop.model.Cliente;
import com.manager.barbershop.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    @Transactional
    public void salvar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    
}
