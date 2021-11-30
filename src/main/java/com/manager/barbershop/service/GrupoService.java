package com.manager.barbershop.service;

import com.manager.barbershop.model.Grupo;
import com.manager.barbershop.repository.GrupoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrupoService {
    
    private final GrupoRepository grupoRepository;
    
    public List<Grupo> gupos() {
        return grupoRepository.findAll();
    }
    
}
