package com.manager.barbershop.service;

import com.manager.barbershop.model.Cliente;
import com.manager.barbershop.repository.ClienteRepository;
import com.manager.barbershop.webconfig.StorageCloudnary;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    private final StorageCloudnary storageCloudnary;
    
    @Transactional
    public void salvar(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    
    @Transactional
    public void salvar(Cliente cliente, MultipartFile multipartFile) {
        String nomeArquivo = "";
        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            cliente.setNomeFoto(fileName.substring(0, fileName.lastIndexOf(".")));
            cliente.setExtensao(extension);
            Cliente novo = clienteRepository.save(cliente);
            nomeArquivo =  novo.getId().toString()+"-"+cliente.getNomeFoto();
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
