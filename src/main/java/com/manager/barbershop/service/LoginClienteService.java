package com.manager.barbershop.service;

import com.manager.barbershop.model.LoginCliente;
import com.manager.barbershop.repository.LoginClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginClienteService {
    
    private final LoginClienteRepository clienteRepository;
    
    
    public void usuarioExistente(LoginCliente loginCliente) {
        clienteRepository.findByEmailAndSenha(loginCliente.getEmail(), loginCliente.getSenha())
        .map(usuario -> {
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
}
