/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Facade;

import com.PorteriaV31.Facade.*;
import com.PorteriaV31.Entities.Estados;
import com.PorteriaV31.Entities.Vehiculos;
import com.PorteriaV31.Utils.Constants;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kmilo
 */
@Stateless
public class VehiculosFacade extends AbstractPersistenceFacade<Vehiculos>{

    @PersistenceContext(unitName = "PorteriaV3.1PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public VehiculosFacade() {
        super(Vehiculos.class);
    }

    @Override
    public void setEmbeddableKeys() {
        //Nothing To Do Here
    }

    @Override
    public void initializeEmbeddableKey() {
        //Nothing To Do Here
    }

    @Override
    public void prepareCreate() {
        entity.setEstado(new Estados(Constants.STATUS_ENTRY));
        prepareUpdate();
    }

    @Override
    public void prepareUpdate() {
        assignParametersToUpdate();
    }
    
}