package com.manager.barbershop.service;

import com.manager.barbershop.model.Usuario;
import com.manager.barbershop.security.UsuarioSistema;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public Usuario getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = ((UsuarioSistema) authentication.getPrincipal()).getUsuario();
        return usuario;
    }

}
