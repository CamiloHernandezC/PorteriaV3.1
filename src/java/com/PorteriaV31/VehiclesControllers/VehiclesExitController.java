package com.PorteriaV31.VehiclesControllers;

import com.PorteriaV31.Entities.Vehiculos;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Navigation;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


@Named("vehiclesExitController")
@ViewScoped
public class VehiclesExitController extends VehiclesIOController{
    
    @Override
    public void buscarPorPlaca(){
        Vehiculos vehicle;
        vehicle = ejbBusinessExitVehicles.searchVehicle(selected.getVehiculos().getPlaca());
        if(vehicle == null){
            JsfUtil.addErrorMessage("Error: El vehiculo no se registro al ingresar intente con otro.");
            return;
        }
        //Assing vehicles to vehicle sucursal.
        selected.setVehiculos(vehicle);
        bloquearPlaca=true;
        bloquearCampos=true;
        bloquearCampoObservacion=false;
        JsfUtil.addSuccessMessage("Info: Vehiculo registrado, tome los datos necesarios y de click en terminar.");
    }
    
    public String exitVehicle(){
        
        int resutlMethot = ejbBusinessExitVehicles.exitVehicle(personFormExitBean.getPersonaSucursal(),movVehiculos,personFormExitBean.getMovPersona(),selected);
        //int resutlMethot = 0;
        switch(resutlMethot){
            case Constants.OK:
                JsfUtil.addSuccessMessage("Registro exitoso");
                return Navigation.PAGE_SELECT_EXIT;
            case Constants.NO_SEARCH:
                JsfUtil.addErrorMessage("Recuerde, el primer paso es BUSCAR el vehiculo");
                return Navigation.PAGE_VEHICLE_EXIT;
            case Constants.UNKNOWN_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo realizar el registro. Contacte al servicio tecnico");
                return Navigation.PAGE_VEHICLE_EXIT;
             case Constants.PERSISTANCE_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo crear el registro de vehiculos. Contacte al servicio tecnico");
                return Navigation.PAGE_VEHICLE_EXIT;
            case Constants.OBJECT_BLOCK:
                JsfUtil.addErrorMessage("El vehiculo que intenta ingresar esta bloqueado.");
                return Navigation.PAGE_VEHICLE_EXIT;
        }
        JsfUtil.addErrorMessage("Algo salio mal. Contacte al servicio tecnico");
        return Navigation.PAGE_VEHICLE_EXIT;
    }
   
}
