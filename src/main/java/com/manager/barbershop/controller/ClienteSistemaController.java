package com.manager.barbershop.controller;


import com.manager.barbershop.exception.NegocioException;
import com.manager.barbershop.model.ClienteSistema;
import com.manager.barbershop.service.ClienteSistemaService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/cliente-sistema")
@RequiredArgsConstructor
public class ClienteSistemaController {
    
    private final ClienteSistemaService clienteSistemaService;
    
    @GetMapping("/novo")
    public ModelAndView index(ClienteSistema clienteSistema, Model model) {
        model.addAttribute("clienteSistema", clienteSistema);
        return new ModelAndView("clientesistema/Novo");
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid ClienteSistema clienteSistema, Model model,BindingResult result,RedirectAttributes attributes ) {
        try {
            if (result.hasErrors()) {
                return index(clienteSistema,model);
            }
            clienteSistemaService.salvar(clienteSistema);
        } catch (NegocioException ex) {
            ObjectError error = new ObjectError("erro", ex.getMessage());
            result.addError(error);
            return index(clienteSistema,model);
        }
        attributes.addFlashAttribute("mensagem", "Novo clinete cadastrado!");
        return new ModelAndView("redirect:/cliente-sistema", HttpStatus.CREATED);
    }
    
}
