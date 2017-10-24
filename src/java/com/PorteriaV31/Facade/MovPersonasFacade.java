/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Facade;

import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Entities.Porterias;
import com.PorteriaV31.Querys.Querys;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class MovPersonasFacade extends AbstractPersistenceFacade<MovPersonas> {

    @PersistenceContext(unitName = "PorteriaV3.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovPersonasFacade() {
        super(MovPersonas.class);
    }

    @Override
    public void setEmbeddableKeys() {
        //Nothing to do here.
    }

    @Override
    public void initializeEmbeddableKey() {
        //Nothing to do here
    }

    @Override
    public void prepareCreate() {
        calculatePrimaryKey(Querys.MOV_PERSONA_CLI_PRIMARY_KEY);
        //TODO Assing real Entry
        entity.setPorteria(new Porterias(1));
        prepareUpdate();
    }

    @Override
    public void prepareUpdate() {
        assignParametersToUpdate();
    }
    
}
