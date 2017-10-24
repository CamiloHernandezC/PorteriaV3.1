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
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author amorales
 */
@Named
@ViewScoped
public class CompleteExitPersonBean extends CompletePersonBean{
   
    @EJB
    private PersonasBusinessExit personasExitControl;
       
    @Inject
    private PersonFormExitBean personFormExitBean;
    
    public String manualExit(){
        
        Result result = personasExitControl.findPersonByDocument(String.valueOf(docType), docNumber);
        if (result.errorCode == Constants.NO_RESULT_EXCEPTION) {
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("Please_Register"));
            return Navigation.PAGE_COMPLETE_EXIT;
        }
        persona = (Personas) result.result;
        if (personasExitControl.verifyBlockedPerson(persona)) {
            return null;
        }
        personasExitControl.loadSpecificPersonByEntry(persona);
        personFormExitBean.obtenerFotoCarpeta(String.valueOf(persona.getIdPersona()));
        personFormExitBean.setPersona(persona);
        
        return Navigation.PAGE_PERSON_EXIT;
    }
    
    public void entryByCodeReader(boolean fromExpress){
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
        Result result = personasExitControl.findByCodeReader(code,persona);
        if(result.errorCode==Constants.UNKNOWN_EXCEPTION){
            JsfUtil.addErrorMessage("Formato no completado correctamente.");
            return;
        }
        if(result.errorCode==Constants.NO_RESULT_EXCEPTION){
            JsfUtil.redirectTo(Navigation.PAGE_COMPLETE_EXIT);
            return;
        }
        persona = (Personas) result.result;
        if (personasExitControl.verifyBlockedPerson(persona)) {
            return;
        }
        personFormExitBean.setPersona(persona);
        JsfUtil.redirectTo(Navigation.PAGE_PERSON_EXIT);
    }
}
