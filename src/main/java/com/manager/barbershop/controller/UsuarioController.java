package com.manager.barbershop.controller;

import com.manager.barbershop.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @GetMapping
    public ModelAndView pageNovo(Usuario usuario) {
        return new ModelAndView("usuario/Novo");
    }
    
}
