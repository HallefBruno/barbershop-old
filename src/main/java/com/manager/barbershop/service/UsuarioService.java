package com.manager.barbershop.service;

import com.manager.barbershop.model.Usuario;
import com.manager.barbershop.repository.UsuarioRepository;
import com.manager.barbershop.security.UsuarioSistema;
import com.manager.barbershop.webconfig.StorageCloudnary;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final StorageCloudnary storageCloudnary;
    private final PasswordEncoder passwordEncoder;

    public Usuario getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = ((UsuarioSistema) authentication.getPrincipal()).getUsuario();
        return usuario;
    }
    
    @Transactional
    public void salvar(Usuario usuario, MultipartFile multipartFile) {
        String nomeArquivo = "";
        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            usuario.setNomeFoto(fileName.substring(0, fileName.lastIndexOf(".")));
            usuario.setExtensao(extension);
            usuario.setClienteSistema(getUsuarioLogado().getClienteSistema());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            Usuario novo = usuarioRepository.save(usuario);
            nomeArquivo =  novo.getId().toString()+"-"+usuario.getNomeFoto();
            storageCloudnary.uploadFoto(multipartFile.getBytes(),nomeArquivo);
        } catch(IOException ex) {
            try {
                storageCloudnary.deleteFoto(nomeArquivo);
            } catch (IOException ex1) {
                throw new RuntimeException(ex1.getMessage());
            }
            throw new RuntimeException(ex.getMessage());
        }
    }

}
