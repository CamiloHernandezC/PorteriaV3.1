package com.PorteriaV31.VehiclesControllers;

import com.PorteriaV31.Entities.Vehiculos;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Navigation;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


@Named("vehiclesEntryController")
@ViewScoped
public class VehiclesEntryController extends VehiclesIOController{
    
    @Override
    public void buscarPorPlaca(){
        Vehiculos vehicle;
        vehicle = ejbBusinessEntryVehicles.searchVehicle(selected.getVehiculos().getPlaca());
        bloquearCampoObservacion=false;
        bloquearPlaca=true;
        if(vehicle == null){
            JsfUtil.addErrorMessage("Error: No se enconotro el vehiculo,por favor pegistrelo");
            bloquearCampos=false;
            return;
        }
        //Assing vehicles to vehicle sucursal.
        selected.setVehiculos(vehicle);
        bloquearCampos=true;
        JsfUtil.addSuccessMessage("Info: Vehiculo registrado, tome los datos necesarios y de click en terminar.");
    }
    
    public String entryVehicle(){
        
        int resutlMethot = ejbBusinessEntryVehicles.entryVehicle(selected,personFormEntryBean.getPersonaSucursal(),movVehiculos,personFormEntryBean.getMovPersona());
        switch(resutlMethot){
            case Constants.OK:
                JsfUtil.addSuccessMessage("Registro exitoso");
                return Navigation.PAGE_SELECT_ENTRY;
            case Constants.NO_SEARCH:
                JsfUtil.addErrorMessage("Recuerde, el primer paso es BUSCAR el vehiculo");
                return Navigation.PAGE_VEHICLE_ENTRY;
            case Constants.UNKNOWN_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo realizar el registro. Contacte al servicio tecnico");
                return Navigation.PAGE_VEHICLE_ENTRY;
             case Constants.PERSISTANCE_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo crear el registro de vehiculos. Contacte al servicio tecnico");
                return Navigation.PAGE_VEHICLE_ENTRY;
            case Constants.OBJECT_BLOCK:
                JsfUtil.addErrorMessage("El vehiculo que intenta ingresar esta bloqueado.");
                return Navigation.PAGE_VEHICLE_ENTRY;
        }
        JsfUtil.addErrorMessage("Algo salio mal. Contacte al servicio tecnico");
        return Navigation.PAGE_VEHICLE_ENTRY;
    }
}
