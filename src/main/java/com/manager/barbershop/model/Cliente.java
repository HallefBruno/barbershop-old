package com.manager.barbershop.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Entity
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Column(nullable = false, length = 100)
    private String nome;
    
    @NotEmpty
    @Column(nullable = false, length = 11)
    private String telefone;
    
    @Email
    @NotEmpty
    @Column(nullable = false, length = 200, unique = true)
    private String email;
    
    @NotEmpty
    @Column(nullable = false, length = 100)
    private String senha;
    
    @Column(nullable = false)
    private Boolean isBarbeiro;
    
    @Column(nullable = false, name = "nome_foto", unique = true)
    private String nomeFoto;
    
    @Column(nullable = false)
    private String extensao;
    
    @PrePersist
    @PreUpdate
    private void prePersistPreUpdate() {
        this.telefone = StringUtils.getDigits(this.telefone);
        this.telefone = StringUtils.strip(this.telefone);
        this.nome = StringUtils.strip(this.nome);
        this.email = StringUtils.strip(this.email);
        this.senha = StringUtils.strip(this.senha);
    }
    
}
