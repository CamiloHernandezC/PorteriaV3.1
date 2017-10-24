/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Facade;

import com.PorteriaV31.Entities.EmpresaOrigen;
import com.PorteriaV31.Entities.Estados;
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
public class EmpresaOrigenFacade extends AbstractPersistenceFacade<EmpresaOrigen> {

    @PersistenceContext(unitName = "PorteriaV3.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaOrigenFacade() {
        super(EmpresaOrigen.class);
    }
    
    public EmpresaOrigen buscarEmpresaNombre(String nombre1) {
        StringBuilder sQuery = new StringBuilder(Querys.EMPRESA_ORIGEN_NAME);
        sQuery.append(nombre1);
        sQuery.append("'");
        EmpresaOrigen empresa = (EmpresaOrigen) findByQuery(sQuery.toString(), true).result;
        return empresa;
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
        calculatePrimaryKey(Querys.EMPRESA_ORIGEN_PRIMARY_KEY);
        entity.setEstado(new Estados(Constants.STATUS_ENTRY));
        prepareUpdate();
    }

    @Override
    public void prepareUpdate() {
        assignParametersToUpdate(); //To change body of generated methods, choose Tools | Templates.
    }
}
