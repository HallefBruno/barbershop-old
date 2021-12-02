package com.manager.barbershop.controller;

import com.manager.barbershop.exception.NegocioException;
import com.manager.barbershop.model.Usuario;
import com.manager.barbershop.service.GrupoService;
import com.manager.barbershop.service.UsuarioService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final GrupoService grupoService;
    private final UsuarioService usuarioService;
    
    @GetMapping
    public ModelAndView pageNovo(Usuario usuario, Model model) {
        model.addAttribute("grupos", grupoService.gupos());
        model.addAttribute("usuario", usuario);
        model.addAttribute("clienteSistema", usuarioService.getUsuarioLogado().getClienteSistema());
        return new ModelAndView("usuario/Novo");
    }
    
    @PostMapping
    public ModelAndView salvar(@RequestParam("image") MultipartFile multipartFile, @Valid Usuario usuario, Model model,BindingResult result,RedirectAttributes attributes ) {
        try {
            if (result.hasErrors()) {
                return pageNovo(usuario,model);
            }
            usuarioService.salvar(usuario,multipartFile);
        } catch (NegocioException ex) {
            ObjectError error = new ObjectError("erro", ex.getMessage());
            result.addError(error);
            return pageNovo(usuario,model);
        }
        attributes.addFlashAttribute("mensagem", "Novo usuário cadastrado!");
        return new ModelAndView("redirect:/usuario", HttpStatus.CREATED);
    }
    
}
