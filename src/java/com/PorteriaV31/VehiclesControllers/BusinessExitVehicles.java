/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.VehiclesControllers;

import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Entities.MovVehiculos;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Entities.VehiculosSucursal;
import com.PorteriaV31.Utils.Constants;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author amorales
 */
@Stateless
public class BusinessExitVehicles extends BusinessVehicles {

   
    public int exitVehicle(PersonasSucursal personasSucursal,MovVehiculos movVehiculo,MovPersonas movPersona,VehiculosSucursal vehiculoSucursal){
        if (!buscar) {
            //Boolean searchVehicle is alredy in false. it didn´t search the vehicle.
            return Constants.NO_SEARCH;
        }
        if(!recordExitMovement(personasSucursal,movPersona,movVehiculo,vehiculoSucursal)){
            return Constants.PERSISTANCE_EXCEPTION;
        }
        return Constants.OK;
    
    }
    
     // <editor-fold desc="SALIDA" defaultstate="collapsed">
    private boolean recordExitMovement(PersonasSucursal personaSucursal,MovPersonas movPersona, MovVehiculos movVehiculo,VehiculosSucursal vehiculoSucursal) {
        List<MovVehiculos> movVehiclesList = (List<MovVehiculos>) verifyEntry(vehiculoSucursal).result;
        if (movVehiclesList.isEmpty()) {
            return recordForcedIn(vehiculoSucursal,movVehiculo,personaSucursal,movPersona);
        }
        return recordExit(movPersona,movVehiclesList.get(0)) == Constants.OK;
    }
    
    private int recordExit(MovPersonas movPersona, MovVehiculos movVehiculo) {
       Date actualDate = new Date();
       movVehiculo.setFechaSalida(actualDate);
       movVehiculo.setHoraSalida(actualDate);
       movVehiculo.setMomentoSalida(actualDate);
       movVehiculo.setMovPersonaSalida(movPersona);
       return ejbFacadeMovVehiculos.update(movVehiculo).errorCode;
    }

    private boolean recordForcedIn(VehiculosSucursal vehiculosSucursal,MovVehiculos movVehiculos,PersonasSucursal personasSucursal, MovPersonas movPersonas) {
       
       movVehiculos = prepareEntityToCreate(vehiculosSucursal,movVehiculos,personasSucursal,movPersonas);
      
       movVehiculos.setMomentoSalida(movVehiculos.getMomentoEntrada());
       movVehiculos.setFechaSalida(movVehiculos.getFechaEntrada());
       movVehiculos.setHoraSalida(movVehiculos.getHoraEntrada());
       movVehiculos.setMovPersonaEntrada(movPersonas);
       movVehiculos.setMovPersonaSalida(movPersonas);
       movVehiculos.setIngresoForzado(true);
       return ejbFacadeMovVehiculos.create(movVehiculos).errorCode==Constants.OK;
    }
    
    //</editor-fold>
    
}
