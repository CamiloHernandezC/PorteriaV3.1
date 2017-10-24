/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.VehiclesControllers;

import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Entities.MovVehiculos;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Entities.Vehiculos;
import com.PorteriaV31.Entities.VehiculosSucursal;
import com.PorteriaV31.Facade.MovVehiculosFacade;
import com.PorteriaV31.Querys.Querys;
import com.PorteriaV31.Utils.Result;
import com.PorteriaV31.Facade.VehiculosFacade;
import com.PorteriaV31.Facade.VehiculosSucursalFacade;
import java.util.Date;
import javax.ejb.EJB;

/**
 *
 * @author amorales
 */
public class BusinessVehicles{

    //EJB
    @EJB
    protected VehiculosFacade ejbFacadeVehicles;
    @EJB
    protected VehiculosSucursalFacade ejbFacadeVehiclesSuc;
    @EJB
    protected MovVehiculosFacade ejbFacadeMovVehiculos;

    //Aditional properties to control.
    public boolean buscar;

    // <editor-fold desc="SEARCH VEHICLE" defaultstate="collapsed">
    /**
     * Search vehicle by number of plate in vehicles table.
     *
     * @param placa
     * @return
     */
    public Vehiculos searchVehicle(String placa) {

        StringBuilder squery = new StringBuilder(Querys.VEHICLES_CLI_ALL);
        squery.append(" Where ");
        squery.append(Querys.VEHICLES_CLI_PLACA);
        squery.append(placa);
        squery.append("'");

        //String sQuery = Querys.VEHICLES_CLI_ALL+" Where "+Querys.VEHICLES_CLI_PLACA +placa+"'";
        Vehiculos vehiculo = (Vehiculos) ejbFacadeVehicles.findByQuery(squery.toString(), true).result;
        buscar = true;
        if (vehiculo == null) {
            return null;
        }
        return vehiculo;
    }
    //</editor-fold>

    public Result verifyEntry(VehiculosSucursal vehiculoSucursal) {

        StringBuilder sQuery = new StringBuilder(Querys.MOV_VEHICLES_CLI_ALL);
        sQuery.append(" Where ");
        sQuery.append(Querys.MOV_VEHICLES_CLI_PLACA);
        sQuery.append(vehiculoSucursal.getVehiculos().getPlaca());
        sQuery.append("' and");
        sQuery.append(Querys.MOV_VEHICLE_CLI_MOMENTO_SALIDA_NULL);

        return ejbFacadeMovVehiculos.findByQueryArray(sQuery.toString());
    }
    
    protected MovVehiculos prepareEntityToCreate(VehiculosSucursal vehiculoSucursal, MovVehiculos movVehiculos, PersonasSucursal personaSucursal, MovPersonas movPersonas) {
        
        Date actualDate = new Date();
        
        movVehiculos.setPlaca(vehiculoSucursal.getVehiculos());
        movVehiculos.setSucursal(personaSucursal.getSucursales());
        
        movVehiculos.setMovPersonaEntrada(movPersonas);
        
        movVehiculos.setMomentoEntrada(actualDate);
        movVehiculos.setFechaEntrada(actualDate);
        movVehiculos.setHoraEntrada(actualDate);
        movVehiculos.setSalidaForzosa(false);

        return movVehiculos;
    }

}
