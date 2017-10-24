/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.ManageBeans;


import com.PorteriaV31.Utils.Navigation;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author amorales
 */
@Named
@ViewScoped
public class SelectEntryBean implements Serializable{
    
    public String irCrearVehiculo(){
        return Navigation.PAGE_VEHICLE_ENTRY;
    }
    
    public String irSacarVehiculo(){
        return Navigation.PAGE_VEHICLE_EXIT;
    }
}
