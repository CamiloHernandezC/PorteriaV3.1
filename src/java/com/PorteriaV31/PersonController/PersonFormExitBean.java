/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Navigation;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author amorales
 */
@Named
@SessionScoped
public class PersonFormExitBean extends PersonFormBean{
    
    @EJB
    PersonasBusinessExit personasBusinessExit;
    
    public String exitPerson(){
        
        int resutlMethot =0;
        personaSucursal=personasBusinessExit.getPersonaSucusal();
        personasBusinessExit.exitPerson(persona,personaSucursal);
        movPersona=personasBusinessExit.getMovPersona();
        personasBusinessExit.clean();
        switch(resutlMethot){
            case Constants.OK:
                JsfUtil.addSuccessMessage("Registro exitoso");
                return Navigation.PAGE_SELECT_EXIT;
            case Constants.UNKNOWN_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo realizar el registro. Contacte al servicio tecnico");
                return Navigation.PAGE_PERSON_EXIT;
             case Constants.PERSISTANCE_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo crear el registro de vehiculos. Contacte al servicio tecnico");
                return Navigation.PAGE_PERSON_EXIT;
            case Constants.OBJECT_BLOCK:
                JsfUtil.addErrorMessage("El vehiculo que intenta ingresar esta bloqueado.");
                return Navigation.PAGE_PERSON_EXIT;
        }
        JsfUtil.addErrorMessage("Algo salio mal. Contacte al servicio tecnico");
        return Navigation.PAGE_PERSON_EXIT;
    }
    
}
