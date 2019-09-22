/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hp-14
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "Persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario login(Usuario u){
        Usuario user = null;
        String consulta;
        try {
            
            consulta="FROM Usuario u WHERE u.usuario = ?2 and u.clave = ?3";
            Query query = em.createQuery(consulta);
            query.setParameter(2,u.getUsuario());
            query.setParameter(3,u.getClave());
            List<Usuario> lista = query.getResultList(); //obtiene la lista de la consulta
            if(!lista.isEmpty()){
                user = lista.get(0);
                return user;
            }
        } catch (Exception e) {
            throw e;
        }
        return user;
    }
}
