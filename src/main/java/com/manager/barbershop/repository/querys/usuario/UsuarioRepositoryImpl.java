
package com.manager.barbershop.repository.querys.usuario;

import com.manager.barbershop.model.Usuario;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Usuario> porEmailEAtivo(String email) {
        return manager
            .createQuery("select u from Usuario u where lower(u.email) = lower(:email) and u.ativo = true", Usuario.class)
            .setParameter("email", email).getResultList().stream().findFirst();
    }

    @Override
    public List<String> permissoes(Usuario usuario) {
        return manager.createQuery("select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
            .setParameter("usuario", usuario)
            .getResultList();
    }
    
}
