/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.ManageBeans;

import com.PorteriaV31.PersonController.PersonFormEntryBean;
import com.PorteriaV31.PersonController.PersonFormExitBean;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Navigation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author amorales
 */
@Named
@RequestScoped
public class IndexBean {
    
    @Inject
    PersonFormEntryBean personFormEntryBean;
    
    @Inject
    PersonFormExitBean personFormExitBean;
    
    //<editor-fold desc="NAVEGACION" defaultstate="collapsed">
    public String irConfiguracion(){
        return Navigation.PAGE_CONFIGURATION;
    }
    
    public void irContactanos(){
        JsfUtil.showModal("diagContact");
    }
    
    public String irIndex(){
        return Navigation.PAGE_INDEX;
    }
    
    public String irIngresoManual(){
        personFormEntryBean.cleanPerson();
        personFormExitBean.cleanPerson();
        return Navigation.PAGE_COMPLETE_ENTRY;
    }
    
    public String irIngresoExpress(){
        personFormEntryBean.cleanPerson();
        personFormExitBean.cleanPerson();
        return Navigation.PAGE_EXPRESS_ENTRY;
    }
    
      public String irSalidaManual(){
        personFormEntryBean.cleanPerson();
        personFormExitBean.cleanPerson();
        return Navigation.PAGE_COMPLETE_EXIT;
    }
    
    public String irSalidaExpress(){
        personFormEntryBean.cleanPerson();
        personFormExitBean.cleanPerson();
        return Navigation.PAGE_EXPRESS_EXIT;
    }
    
    //</editor-fold>
    
    public void showModalHelpVerMovimientos(){
        JsfUtil.showModal("dlgHelpMov");
    }

}
