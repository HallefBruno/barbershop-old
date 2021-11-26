package com.manager.barbershop.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@EqualsAndHashCode
@DynamicUpdate
@Table(name = "cliente_sistema")
public class ClienteSistema implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Long id;
    
    @NotBlank(message = "Nome do comercio não pode ter espaços em branco!")
    @NotEmpty(message = "Nome do comercio não pode ser vazio!")
    @NotNull(message = "Nome do comercio não pode ser null!")
    @Column(name = "nome_comercio", nullable = false)
    private String nomeComercio;
    
    @NotBlank(message = "CPF/CNPJ não pode ter espaços em branco!")
    @NotEmpty(message = "CPF/CNPJ não pode ser vazio!")
    @NotNull(message = "CPF/CNPJ não pode ser null!")
    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    private String cpfCnpj;
    
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;
    
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;
    
    @NotBlank(message = "Estado não pode ter espaços em branco!")
    @NotEmpty(message = "Estado não pode ser vazio!")
    @NotNull(message = "Estado não pode ser null!")
    @Column(name = "estado", nullable = false)
    private String estado;
    
    @NotBlank(message = "Cidade não pode ter espaços em branco!")
    @NotEmpty(message = "Cidade não pode ser vazio!")
    @NotNull(message = "Cidade não pode ser null!")
    @Column(name = "cidade", nullable = false)
    private String cidade;
    
    @NotBlank(message = "Bairro não pode ter espaços em branco!")
    @NotEmpty(message = "Bairro não pode ser vazio!")
    @NotNull(message = "Bairro não pode ser null!")
    @Column(name = "bairro", nullable = false)
    private String bairro;
    
    @NotBlank(message = "CEP não pode ter espaços em branco!")
    @NotEmpty(message = "CEP não pode ser vazio!")
    @NotNull(message = "CEP não pode ser null!")
    @Column(nullable = false)
    private String cep;
    
    @NotBlank(message = "Logradouro não pode ter espaços em branco!")
    @NotEmpty(message = "Logradouro não pode ser vazio!")
    @NotNull(message = "Logradouro não pode ser null!")
    @Column(nullable = false)
    private String logradouro;
    
    @NotNull(message = "Quantidade usuário não pode ser null!")
    @Column(name = "qtd_usuario", nullable = false)
    private Integer qtdUsuario;
    
    @PreUpdate
    @PrePersist
    private void prePersistPreUpdate() {
        this.bairro = StringUtils.strip(this.bairro);
        this.bairro = this.bairro.toLowerCase();
        this.cidade = StringUtils.strip(this.cidade);
        this.cidade = this.cidade.toLowerCase();
        this.estado = StringUtils.strip(this.estado);
        this.estado = this.estado.toLowerCase();
        this.nomeComercio = StringUtils.strip(this.nomeComercio);
        this.nomeComercio = this.nomeComercio.toLowerCase();
        this.logradouro = StringUtils.strip(this.logradouro);
        this.logradouro = this.logradouro.toLowerCase();
        this.cep = StringUtils.getDigits(this.cep);
        this.cpfCnpj = StringUtils.getDigits(this.cpfCnpj);
    }
}

