package com.manager.barbershop.controller;

import com.manager.barbershop.model.LoginCliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/novo-agendamento")
public class AgendamentoController {
    
    @GetMapping
    public ModelAndView pageAgendamento(LoginCliente loginCliente) {
        return new ModelAndView("agendamento/Novo");
    }
    
}
