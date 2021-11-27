package com.manager.barbershop.controller;

import com.manager.barbershop.exception.NegocioException;
import com.manager.barbershop.model.LoginCliente;
import com.manager.barbershop.repository.LoginClienteRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = {"/login-cliente","/novo"})
@RequiredArgsConstructor
public class ClienteLoginController {
    
    private final LoginClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping
    public ModelAndView pageInicial(LoginCliente loginCliente) {
        return new ModelAndView("LoginCliente");
    }
    
    @PostMapping
    public ModelAndView validarLoginUsuario(@Valid LoginCliente loginCliente, Model model,BindingResult result,RedirectAttributes attributes ) {
        try {
            if (result.hasErrors()) {
                return pageInicial(loginCliente);
            }
            loginCliente.setSenha(passwordEncoder.encode(loginCliente.getSenha()));
            if(clienteRepository.findByEmailAndSenha(loginCliente.getEmail(), loginCliente.getSenha()).isEmpty()) {
                return new ModelAndView("redirect:/novo/agendamento", HttpStatus.OK);
            }
        } catch (NegocioException ex) {
            ObjectError error = new ObjectError("erro", ex.getMessage());
            result.addError(error);
            return pageInicial(loginCliente);
        }
        attributes.addFlashAttribute("mensagem", "Nenhuma conta encontrada!");
        return pageInicial(loginCliente);
    }
    
    @GetMapping("/agendamento")
    public ModelAndView pageAgendamento(LoginCliente loginCliente) {
        return new ModelAndView("agendamento/Agendar");
    }
}
