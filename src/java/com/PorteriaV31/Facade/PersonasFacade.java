/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Facade;

import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Querys.Querys;
import com.PorteriaV31.Utils.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author chernandez
 */
@Stateless
public class PersonasFacade extends AbstractPersistenceFacade<Personas> {

    @PersistenceContext(unitName = "PorteriaV3.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
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
        entity.setStatus(Constants.STATUS_ENTRY);
        calculatePrimaryKey(Querys.PERSONA_CLI_PRIMARY_KEY);
        prepareUpdate();
    }

    @Override
    public void prepareUpdate() {
        assignParametersToUpdate();
    }
    
}
