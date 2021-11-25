package com.manager.barbershop.webconfig;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class StorageCloudnary {

    private Map configOpenCloudinary() {
        Map chave = ObjectUtils.asMap(
            "cloud_name", "sud",
            "api_key", "414869814418293",
            "api_secret", "mWG1plNyyL8ufVQEiNNF9NnIcZw");
        return chave;
    }
    
    public void uploadFoto(byte[] dataImage,String candidatoId) throws IOException {
        Map conf = ObjectUtils.asMap("public_id", "barbershop-criar-conta/"+candidatoId);
        Cloudinary cloudinary = new Cloudinary(configOpenCloudinary());
        cloudinary.uploader().upload(dataImage, conf);
    }
    
    public void deleteFoto(String candidatoId) throws IOException {
        if(!StringUtils.isBlank(candidatoId)) {
            Map confCandidato = ObjectUtils.asMap("resource_type","image","invalidate","true");
            Cloudinary cloudinary = new Cloudinary(configOpenCloudinary());
            cloudinary.uploader().destroy("barbershop-criar-conta/"+candidatoId,confCandidato);
        }
    }
}
