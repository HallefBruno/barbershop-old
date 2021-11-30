package com.manager.barbershop.controller;

import com.manager.barbershop.model.Usuario;
import com.manager.barbershop.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final GrupoService grupoService;
    
    @GetMapping
    public ModelAndView pageNovo(Usuario usuario, Model model) {
        model.addAttribute("grupos", grupoService.gupos());
        model.addAttribute("usuario", usuario);
        return new ModelAndView("usuario/Novo");
    }
    
}
