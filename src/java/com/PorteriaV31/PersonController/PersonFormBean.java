/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.PhotoUtils;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author amorales
 */
public class PersonFormBean implements Serializable{
    
    protected Personas persona;
    protected PersonasSucursal personaSucursal;
    protected MovPersonas movPersona;
    
    protected byte[] imagen;
    
    // <editor-fold desc="Getters and Setters" defaultstate="collapsed">

    public MovPersonas getMovPersona() {
        return movPersona;
    }

    public void setMovPersona(MovPersonas movPersona) {
        this.movPersona = movPersona;
    }
    
    public Personas getPersona() {
        if(persona==null){
            persona = new Personas();
        }
        //Inicialize movPersonas.
        if(movPersona==null){
            movPersona = new MovPersonas();
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
    
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    //</editor-fold>
 
    public void obtenerFotoCarpeta(String idPersona) {
        try {
        imagen = PhotoUtils.obtenerFotoCarpeta(idPersona, Constants.OBJECT_PERSON);
         } catch (IOException ex) {
            System.out.println("Manual Entry: Error al obtener la foto de la personas con id: " + idPersona);
            System.out.println("Exception Manual Entry" + ex);
        }
    }
    
    // <editor-fold desc="Clean and Cancel" defaultstate="collapsed">
    public void cleanPerson() {
        persona =null;
        personaSucursal=null;
        movPersona=null;
        imagen = null;
    }
    
     public void cancel() {
        cleanPerson();
        //TODO CLEAN BRANCH AND AREA, MOV, EVERITHING
        JsfUtil.cancel();
    }
     //</editor-fold>
     
}
