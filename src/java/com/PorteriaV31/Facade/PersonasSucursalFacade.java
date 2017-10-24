/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Facade;

import com.PorteriaV31.Entities.Entidades;
import com.PorteriaV31.Entities.Estados;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Entities.PersonasSucursalPK;
import com.PorteriaV31.Utils.Constants;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class PersonasSucursalFacade extends AbstractPersistenceFacade<PersonasSucursal> {

    @PersistenceContext(unitName = "PorteriaV3.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasSucursalFacade() {
        super(PersonasSucursal.class);
    }

    @Override
    public void setEmbeddableKeys() {
        entity.getPersonasSucursalPK().setSucursal(entity.getSucursales().getIdSucursal());
        entity.getPersonasSucursalPK().setIdPersona(entity.getPersonas().getIdPersona());
    }

    @Override
    public void initializeEmbeddableKey() {
        entity.setPersonasSucursalPK(new PersonasSucursalPK());
    }

    @Override
    public void prepareCreate() {
        entity.setEstado(new Estados(Constants.STATUS_ENTRY));
        entity.setEntidad(new Entidades(Constants.ENTITY_VISITANT));
        prepareUpdate();
    }

    @Override
    public void prepareUpdate() {
        assignParametersToUpdate();
    }
    
}
