/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Facade;

import com.PorteriaV31.Entities.Theme;
import com.PorteriaV31.Entities.Usuarios;
import com.PorteriaV31.Utils.JsfUtil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class UsuariosFacade extends AbstractPersistenceFacade<Usuarios> {

    @PersistenceContext(unitName = "PorteriaV3.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public void setEmbeddableKeys() {
        //Nothing to do here
    }

    @Override
    public void initializeEmbeddableKey() {
        //Nothing to do here
    }

    @Override
    public void prepareCreate() {
        prepareUpdate();
    }

    @Override
    public void prepareUpdate() {
        assignParametersToUpdate();
    }

    public void saveTheme(Theme selected) {
        Usuarios user = JsfUtil.getSessionUser();
        user.setTema(selected);
        update(user);
    }
    
}
