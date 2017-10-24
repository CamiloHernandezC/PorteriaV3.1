/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.ManageBeans.BranchOfficeBean;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Navigation;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author amorales
 */
public class CompletePersonBean implements Serializable{
    
    @Inject
    protected BranchOfficeBean branchOfficeBean;
    
    @Inject
    protected PersonFormEntryBean personFormEntryBean;
    
    @EJB
    protected PersonasBusinessEntry personasEntryControl;
   
    protected int docType;
    
    protected String docNumber;
    protected String code;
    
    protected boolean inputMethod = true;//true: manual entry; false: code reader
    
    protected Personas persona;
    
    protected PersonasSucursal personaSucursal;
    
    //protected MovPersonas movPersona; 
    
    // <editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isInputMethod() {
        return inputMethod;
    }

    public void setInputMethod(boolean inputMethod) {
        this.inputMethod = inputMethod;
    }
    
    public Personas getPersona() {
        if(persona == null){
            persona = new Personas();
        }
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public PersonasSucursal getPersonaSucursal() {
        if(personaSucursal==null){
            personaSucursal=new PersonasSucursal();
        }
        return personaSucursal;
    }

    public void setPersonaSucursal(PersonasSucursal personaSucursal) {
        this.personaSucursal = personaSucursal;
    }
    
    
    //</editor-fold>

    public void cleanCode(){
        code = null;
    }
    
    public void cleanPersons(){
        persona=null;
        personaSucursal=null;
    }
    
    public String cancel(){
        return Navigation.PAGE_INDEX;
    }
    
    public void hideBlockDialog(){
        cleanCode();
        JsfUtil.exitModal("blockedDialog");
    }
    
    public PersonasSucursal loadBranchOffice(){
        branchOfficeBean.loadBranchOffices();
        branchOfficeBean.isShowBranchOffice();//Load default branch office if appropiate
        if(branchOfficeBean.getSelectedBranchOffice()!=null){
            PersonasSucursal personasSucursal = new PersonasSucursal();
            personasSucursal.setSucursales(branchOfficeBean.getSelectedBranchOffice());
            return personasSucursal;
        }
        return null;
    }

}
