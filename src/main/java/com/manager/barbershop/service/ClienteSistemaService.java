package com.manager.barbershop.service;

import com.manager.barbershop.model.ClienteSistema;
import com.manager.barbershop.repository.ClienteSistemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteSistemaService {

    private final ClienteSistemaRepository clienteSistemaRepository;

    @Transactional
    public void salvar(ClienteSistema clienteSistema) {
        clienteSistemaRepository.save(clienteSistema);
    }

}
