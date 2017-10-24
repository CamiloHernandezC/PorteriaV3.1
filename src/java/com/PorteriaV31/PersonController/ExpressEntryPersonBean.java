/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Utils.BundleUtils;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Navigation;
import com.PorteriaV31.Utils.Result;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author amorales
 */
@Named
@ViewScoped
public class ExpressEntryPersonBean extends CompletePersonBean {

    public void entryByCodeReader() {
        
        if (!code.startsWith("C,") && !code.startsWith("B,")) {//ID CARD (C: CEDULA) OR BAR CODE (B)
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("UNACCEPTED_FORMAT"));
            cleanCode();
            return;
        }
        //Selected is assing in this methot, if error code is no result exception,
        //the view use the assing.
        Result result = personasEntryControl.findByCodeReader(code, persona);

        if (result.errorCode == Constants.UNKNOWN_EXCEPTION) {
            JsfUtil.addErrorMessage("Formato no completado correctamente.");
            cleanCode();
            return;
        }
        //loadBranchOffice();
        if (result.errorCode == Constants.NO_RESULT_EXCEPTION) {
            personFormEntryBean.disableNoEditableFields(false);
            personFormEntryBean.setPersona(persona);
            JsfUtil.showModal("expressDialog");
            cleanCode();
            return;
        }
        if (code.startsWith("C,")) {//Cedula
            personaSucursal=loadBranchOffice();
            persona = (Personas) result.result;
        }
        if (code.startsWith("B,")) {//Codigo de Barras
            personaSucursal = (PersonasSucursal) result.result;
            persona = personaSucursal.getPersonas();
            if (personaSucursal.getEstado().getIdEstado() == Constants.STATUS_BLOCKED) {
                JsfUtil.addErrorMessage("La persona que intenta ingresar esta bloqueado.");
                cleanCode();
                return;
            }
        }
        if (personasEntryControl.verifyBlockedPerson(persona)) {
            cleanCode();
            return;
        }
        boolean fechas = personFormEntryBean.verifyDatesPerson(persona);
        if (code.startsWith("B")) {
            personFormEntryBean.setPersonaSucursal((PersonasSucursal) result.result);
            if (!fechas) {
                JsfUtil.showModal("fechasDialog");
                cleanCode();
                return;
            }
            personasEntryControl.recordEntryMovement(persona, personaSucursal);
            JsfUtil.redirectTo(Navigation.PAGE_EXPRESS_ENTRY);
            cleanCode();
            return;
        }
        if (code.startsWith("C")) {
            cleanCode();
            JsfUtil.showModal("sucursalDialog");
        }
    }

    public String redirecToPersonFormEntry() {
        personFormEntryBean.showFieldsPerson();
        personFormEntryBean.setPersonaSucursal(loadBranchOffice());
        cleanPersons();
        return Navigation.PAGE_PERSON_REGISTER;
    }

    public String entryPerson() {

        int resutlMethot = personasEntryControl.entryPerson(null, persona, personaSucursal);
        cleanPersons();
        switch (resutlMethot) {
            case Constants.OK:
                JsfUtil.addSuccessMessage("Registro exitoso");
                return Navigation.PAGE_EXPRESS_ENTRY;
            case Constants.UNKNOWN_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo realizar el registro. Contacte al servicio tecnico");
                return Navigation.PAGE_EXPRESS_ENTRY;
            case Constants.PERSISTANCE_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo crear el registro de persona. Contacte al servicio tecnico");
                return Navigation.PAGE_EXPRESS_ENTRY;
            case Constants.OBJECT_BLOCK:
                JsfUtil.addErrorMessage("La persona que intenta ingresar esta bloqueado.");
                return Navigation.PAGE_EXPRESS_ENTRY;
        }
        JsfUtil.addErrorMessage("Algo salio mal. Contacte al servicio tecnico");
        return Navigation.PAGE_EXPRESS_ENTRY;
    }
    
    @PreDestroy
    public void destroy(){
        System.out.println("Destruido");
    }
    
    @PostConstruct
    private void initializePerson(){
        if(persona==null){
            persona= new Personas();
        }
        if(personaSucursal == null){
            personaSucursal = new PersonasSucursal();
        }
    }

}
