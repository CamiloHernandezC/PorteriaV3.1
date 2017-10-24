/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Utils;


import java.io.Serializable;
import java.util.Date;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author a.morales
 */

@Named("helpUtils")
@RequestScoped
public class HelpUtils implements Serializable{
    
    private String Nombre;
    private String correoContacto;
    private String Descripcion;
    private String ciudad;
    
    @EJB
    Email email;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }
    
    public void showModalHelpConfiguration(){
        JsfUtil.showModal("dlgHelpTheme");
    }
    
    public void showModalHelpContact(){
        JsfUtil.showModal("dlgHelpContact");
    }
    
    public void showModalHelpSelectEntry(){
        JsfUtil.showModal("dlgHelpSelectEntry");
    }
    
    public void showModalHelpSelectExit(){
        JsfUtil.showModal("dlgHelpSelectExit");
    }
    
    public void showModalHelpVehicleEntry(){
        JsfUtil.showModal("dlgHelpVehicleEntry");
    }
    
    public void showModalHelpVehicleExit(){
        JsfUtil.showModal("dlgHelpVehicleExit");
    }
    
    public void enviarCorreoContacto(){
        String body= new Date()+"\n"
                + " \n"
                + "Email enviado por: \n"
                + "\n"
                + Nombre +"\n"
                + "\n"
                + "ubicado en la ciudad de : \n"
                + "\n"
                + ciudad +"\n"
                + "\n"
                + "Donde la descripcion fue : \n"
                + "\n"
                + Descripcion +"\n"
                + "\n"
                + "Con direccion o telefono de contacto \n"
                + "\n"
                + correoContacto +"\n"
                +"\n"
                + "Best regards, Support Team";
        String to = "andresmaomorales@gmail.com";
        String subject = "Contacto al Servicio Tecnico";
        email.sendEmail(to,subject, body, null);
        limpiar();
        JsfUtil.addSuccessMessage("Correo electornico enviado al servicio tecnico");
        JsfUtil.exitModal("diagContact");
    }

    public void limpiar() {
        ciudad = null;
        Nombre=null;
        Descripcion=null;
        correoContacto=null;
    }
    
}
