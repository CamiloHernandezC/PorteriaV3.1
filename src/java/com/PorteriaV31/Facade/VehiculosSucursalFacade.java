/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Facade;

import com.PorteriaV31.Facade.*;
import com.PorteriaV31.Entities.Estados;
import com.PorteriaV31.Entities.VehiculosSucursal;
import com.PorteriaV31.Entities.VehiculosSucursalPK;
import com.PorteriaV31.Utils.Constants;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kmilo
 */
@Stateless
public class VehiculosSucursalFacade extends AbstractPersistenceFacade<VehiculosSucursal> {

    @PersistenceContext(unitName = "PorteriaV3.1PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public VehiculosSucursalFacade() {
        super(VehiculosSucursal.class);
    }

    @Override
    public void setEmbeddableKeys() {
        entity.getVehiculosSucursalPK().setSucursal(entity.getSucursales().getIdSucursal());
        entity.getVehiculosSucursalPK().setPlaca(entity.getVehiculos().getPlaca());
    }

    @Override
    public void initializeEmbeddableKey() {
       entity.setVehiculosSucursalPK(new VehiculosSucursalPK());
    }

    @Override
    public void prepareCreate() {
        initializeEmbeddableKey();
        entity.setEstado(new Estados(Constants.STATUS_ENTRY));
        prepareUpdate();
    }

    @Override
    public void prepareUpdate() {
         assignParametersToUpdate();
    }
}
