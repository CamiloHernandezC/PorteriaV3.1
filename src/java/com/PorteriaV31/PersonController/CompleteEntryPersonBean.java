/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Utils.BundleUtils;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Navigation;
import com.PorteriaV31.Utils.Result;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author amorales
 */
@Named
@ViewScoped
public class CompleteEntryPersonBean  extends CompletePersonBean{
   
    public String manualEntry(){
        
        //Carga los campos del formulario.
        personFormEntryBean.showFieldsPerson();
        
        personFormEntryBean.setPersonaSucursal(loadBranchOffice());
        
        Result result = personasEntryControl.findPersonByDocument(String.valueOf(docType), docNumber);
        if (result.errorCode == Constants.NO_RESULT_EXCEPTION) {
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("Please_Register"));
            personFormEntryBean.disableNoEditableFields(false);
            return Navigation.PAGE_PERSON_REGISTER;
        }

        persona = (Personas) result.result;
        if (personasEntryControl.verifyBlockedPerson(persona)) {
            return null;
        }
        
        personFormEntryBean.verifyDatesPerson(persona);
        personFormEntryBean.disableNoEditableFields(true);
        personFormEntryBean.obtenerFotoCarpeta(String.valueOf(persona.getIdPersona()));
        personFormEntryBean.setPersona(persona);
        
        return Navigation.PAGE_PERSON_REGISTER;
    }
    
    public void entryByCodeReader(){
        if (!code.startsWith("C,") && !code.startsWith("B,")) {//ID CARD (C: CEDULA) OR BAR CODE (B)
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("UNACCEPTED_FORMAT"));
            cleanCode();
            return;
        }
        if (persona == null) {
            persona = new Personas();
        }
        //Selected is assing in this methot, if error code is no result exception,
        //the view use the assing.
        Result result = personasEntryControl.findByCodeReader(code,persona);
        if(result.errorCode==Constants.UNKNOWN_EXCEPTION){
            JsfUtil.addErrorMessage("Formato no completado correctamente.");
            return;
        }
        personFormEntryBean.setPersonaSucursal(loadBranchOffice());
        personFormEntryBean.showFieldsPerson();
        if(result.errorCode==Constants.NO_RESULT_EXCEPTION){
            personFormEntryBean.disableNoEditableFields(false);
            personFormEntryBean.setPersona(persona);
            JsfUtil.redirectTo(Navigation.PAGE_PERSON_REGISTER);
            return;
        }
        persona = (Personas) result.result;
        if (personasEntryControl.verifyBlockedPerson(persona)) {
            return;
        }
        personFormEntryBean.disableNoEditableFields(true);
        personFormEntryBean.verifyDatesPerson(persona);
        personFormEntryBean.setPersona(persona);
        JsfUtil.redirectTo(Navigation.PAGE_PERSON_REGISTER);
    }

    
}
