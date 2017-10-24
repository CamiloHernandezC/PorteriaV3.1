/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Facade;

import com.PorteriaV31.Facade.*;
import com.PorteriaV31.Entities.MovVehiculos;
import com.PorteriaV31.Querys.Querys;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kmilo
 */
@Stateless
public class MovVehiculosFacade extends AbstractPersistenceFacade<MovVehiculos> {

    @PersistenceContext(unitName = "PorteriaV3.1PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public MovVehiculosFacade() {
        super(MovVehiculos.class);
    }

    @Override
    public void setEmbeddableKeys() {
        //Nothing to do here.
    }

    @Override
    public void initializeEmbeddableKey() {
        //Nothing to do here.
    }

    @Override
    public void prepareCreate() {
        calculatePrimaryKey(Querys.MOV_VEHICLES_CLI_PRIMARY_KEY);
        prepareUpdate();
    }

    @Override
    public void prepareUpdate() {
        assignParametersToUpdate();
    }
    
}
