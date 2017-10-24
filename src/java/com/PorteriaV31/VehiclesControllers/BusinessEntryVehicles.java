/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.VehiclesControllers;

import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Entities.MovVehiculos;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Entities.Sucursales;
import com.PorteriaV31.Entities.VehiculosSucursal;
import com.PorteriaV31.Querys.Querys;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.Result;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author amorales
 */
@Stateless
public class BusinessEntryVehicles extends BusinessVehicles{
    
    // <editor-fold desc="SEARCH VEHICLE SUCURSAL" defaultstate="collapsed">
    /**
     * Search by Placa and Sucursal in the vehicles Sucursal table.
     *
     * @param selected - Vehicle sucursal that bring the information to query
     * @return
     */
    private VehiculosSucursal searchVehicleSucursal(VehiculosSucursal selected, PersonasSucursal personaSucursal) {

        //String squery = Querys.VEHICLES_SUCURSAL_CLI_ALL+" Where "+Querys.VEHICLES_SUCURSAL_CLI_PLACA +selected.getVehiculos().getPlaca()+"' and "+Querys.VEHICLES_SUCURSAL_CLI_SUCURSAL+personaSucursal.getSucursales().getIdSucursal()+"'";
        StringBuilder sQuery = new StringBuilder(Querys.VEHICLES_SUCURSAL_CLI_ALL);
        sQuery.append(" Where ");
        sQuery.append(Querys.VEHICLES_SUCURSAL_CLI_PLACA);
        sQuery.append(selected.getVehiculos().getPlaca());
        sQuery.append("' and ");
        sQuery.append(Querys.VEHICLES_SUCURSAL_CLI_SUCURSAL);
        sQuery.append(personaSucursal.getSucursales().getIdSucursal());
        sQuery.append("'");

        selected = (VehiculosSucursal) ejbFacadeVehiclesSuc.findByQuery(sQuery.toString(), false).result;
        return selected;
    }
    //</editor-fold>

    public int entryVehicle(VehiculosSucursal vehiculoSucursal, PersonasSucursal personaSucursal, MovVehiculos movVehiculos, MovPersonas movPersonas) {
        if (!buscar) {
            //Boolean searchVehicle is alredy in false. it didn´t search the vehicle.
            return Constants.NO_SEARCH;
        }
        //Don´t exist vehicle in table vehicles.
        //Comparation is with user beacuse if vehiculoSucursal don't exist this field is 
        //always null.
        if (vehiculoSucursal.getVehiculos().getUsuario() == null) {
            //Create a vehicle in vehicles table.
            if (ejbFacadeVehicles.create(vehiculoSucursal.getVehiculos()).errorCode != Constants.OK) {
                //It can not create.
                return Constants.PERSISTANCE_EXCEPTION;
            }
            //Assing Sucursal to Vehicles Sucursal from Person Sucursal.        
            vehiculoSucursal.setSucursales(new Sucursales(personaSucursal.getSucursales().getIdSucursal()));
            //Create a vehicle Sucursal in vehiclesSucursal table.
            if (ejbFacadeVehiclesSuc.create(vehiculoSucursal).errorCode != Constants.OK) {
                //It can not create
                return Constants.PERSISTANCE_EXCEPTION;
            }
            //Create the movment into the table movVehiculos. 
            if (!recordEntryMovement(vehiculoSucursal, movVehiculos, personaSucursal, movPersonas)) {
                return Constants.PERSISTANCE_EXCEPTION;
            }
            return Constants.OK;
        }
        //Exist vehicle in table vehiculoSUcursal.
        VehiculosSucursal vehicleSuc = searchVehicleSucursal(vehiculoSucursal, personaSucursal);
        if (vehicleSuc == null) {
            //Assing Sucursal to Vehicles Sucursal from Person Sucursal.
            vehiculoSucursal.setSucursales(new Sucursales(personaSucursal.getSucursales().getIdSucursal()));
            Result result = ejbFacadeVehiclesSuc.create(vehiculoSucursal);
            if (result.errorCode != Constants.OK) {
                //It can not create
                return Constants.PERSISTANCE_EXCEPTION;
            }
            vehicleSuc = (VehiculosSucursal) result.result;
        }
        vehiculoSucursal = vehicleSuc;
        if (vehiculoSucursal.getVehiculos().getEstado().getIdEstado() == Constants.STATUS_BLOCKED || vehiculoSucursal.getEstado().getIdEstado() == Constants.STATUS_BLOCKED) {
            //Vehicle block in vehicles table and sucursal vehicle table.
            return Constants.OBJECT_BLOCK;
        }
        if (!recordEntryMovement(vehiculoSucursal, movVehiculos, personaSucursal, movPersonas)) {
            return Constants.PERSISTANCE_EXCEPTION;
        }
        return Constants.OK;
    }

    /**
     *
     * @param movVehiculos - Entity that will be persist
     * @return true if the create was succesful
     */
    private boolean recordEntryMovement(VehiculosSucursal vehiculoSucursal, MovVehiculos movVehiculos, PersonasSucursal personaSucursal, MovPersonas movPersonas) {

        List<MovVehiculos> movVehiclesList = (List<MovVehiculos>) verifyEntry(vehiculoSucursal).result;
        if (!movVehiclesList.isEmpty()) {
            recordForcedOut(movVehiclesList);
        }
        return recordEntry(vehiculoSucursal, movVehiculos, personaSucursal, movPersonas) == Constants.OK;
    }

    private void recordForcedOut(List<MovVehiculos> movVehiclesList) {
        for (MovVehiculos mov : movVehiclesList) {
            mov.setFechaSalida(mov.getFechaEntrada());
            mov.setHoraSalida(mov.getHoraEntrada());
            mov.setMomentoSalida(mov.getMomentoEntrada());
            mov.setSalidaForzosa(true);
            mov.setMovPersonaSalida(mov.getMovPersonaEntrada());
            ejbFacadeMovVehiculos.update(mov);
        }
    }

    private int recordEntry(VehiculosSucursal vehiculoSucursal, MovVehiculos movVehiculos, PersonasSucursal personaSucursal, MovPersonas movPersonas) {
        movVehiculos = prepareEntityToCreate(vehiculoSucursal, movVehiculos, personaSucursal, movPersonas);
        return ejbFacadeMovVehiculos.create(movVehiculos).errorCode;
    }

}
