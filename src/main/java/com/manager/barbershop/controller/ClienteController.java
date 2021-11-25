package com.manager.barbershop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @GetMapping
    public ModelAndView pageInit() {
        return new ModelAndView("clientes/CadastroCliente");
    }
    
}
