/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.PorteriaV31.Entities.ConfigForm;
import com.PorteriaV31.Entities.EmpresaOrigen;
import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Facade.ConfigFormFacade;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Navigation;
import com.PorteriaV31.Utils.PhotoUtils;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import org.primefaces.event.CaptureEvent;

/**
 *
 * @author amorales
 */
@Named
@SessionScoped
public class PersonFormEntryBean extends PersonFormBean {

    private boolean disableNoEditableField;
    private boolean disableOtherEnterprise = true;//Starts in true until other origin enterprise is selected

    private String nombreEmpresa;

    @EJB
    protected ConfigFormFacade ejbFacadeConfig;

    @EJB
    private PersonasBusinessEntry personasBusinessEntry;

    // <editor-fold desc="ConfigForm Booleans" defaultstate="collapsed">
    private boolean mostrarARL;
    private boolean mostrarCelular;
    private boolean mostrarEmail;
    private boolean mostrarDireccion;
    private boolean mostrarEPS;
    private boolean mostrarnacimiento;
    private boolean mostrarFecha_vigencia_ARL;
    private boolean mostrarFecha_vigencia_EPS;
    private boolean mostrarDepartamento;
    private boolean mostrarIdExterno;
    private boolean mostrarPais;
    private boolean mostrarMunicipio;
    private boolean mostrarSexo;
    private boolean mostrarTelefono;
    private boolean mostrarTelefonoContacto;
    private boolean mostrarTipo_sanguineo;
    private boolean mostrarFuncionario;
    private boolean mostrarEmpresa;
    private boolean mostrarPersonaContacto;

    public boolean isMostrarPersonaContacto() {
        return mostrarPersonaContacto;
    }

    public boolean isMostrarTelefonoContacto() {
        return mostrarTelefonoContacto;
    }

    public void setMostrarTelefonoContacto(boolean mostrarTelefonoContacto) {
        this.mostrarTelefonoContacto = mostrarTelefonoContacto;
    }

    public boolean isMostrarARL() {
        return mostrarARL;
    }

    public boolean isMostrarCelular() {
        return mostrarCelular;
    }

    public boolean isMostrarEmail() {
        return mostrarEmail;
    }

    public boolean isMostrarDireccion() {
        return mostrarDireccion;
    }

    public boolean isMostrarEPS() {
        return mostrarEPS;
    }

    public boolean isMostrarnacimiento() {
        return mostrarnacimiento;
    }

    public boolean isMostrarFecha_vigencia_ARL() {
        return mostrarFecha_vigencia_ARL;
    }

    public boolean isMostrarFecha_vigencia_EPS() {
        return mostrarFecha_vigencia_EPS;
    }

    public boolean isMostrarDepartamento() {
        return mostrarDepartamento;
    }

    public boolean isMostrarIdExterno() {
        return mostrarIdExterno;
    }

    public boolean isMostrarPais() {
        return mostrarPais;
    }

    public boolean isMostrarMunicipio() {
        return mostrarMunicipio;
    }

    public boolean isMostrarSexo() {
        return mostrarSexo;
    }

    public boolean isMostrarTelefono() {
        return mostrarTelefono;
    }

    public boolean isMostrarTipo_sanguineo() {
        return mostrarTipo_sanguineo;
    }

    public boolean isMostrarFuncionario() {
        return mostrarFuncionario;
    }

    public boolean isMostrarEmpresa() {
        return mostrarEmpresa;
    }

    //</editor-fold>
    // <editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa.toUpperCase();
    }

    public boolean isDisableNoEditableField() {
        return disableNoEditableField;
    }

    public void setDisableNoEditableField(boolean disableNoEditableField) {
        this.disableNoEditableField = disableNoEditableField;
    }

    public boolean isDisableOtherEnterprise() {
        return disableOtherEnterprise;
    }

    public void setDisableOtherEnterprise(boolean disableOtherEnterprise) {
        this.disableOtherEnterprise = disableOtherEnterprise;
    }

    //</editor-fold>
    // <editor-fold desc="Show field of personas in form" defaultstate="collapsed">
    public void showFieldsPerson() {
        List<ConfigForm> array;
        String squery = "SELECT c FROM ConfigForm c WHERE c.formulario ='" + Constants.CONFIGPERSONSFORM + "'";
        array = (List<ConfigForm>) ejbFacadeConfig.findByQueryArray(squery).result;
        for (int i = 0; i < array.size(); i++) {
            ConfigForm modelo = array.get(i);
            String tmp = modelo.getCampo();
            tmp = JsfUtil.quitaEspacios(tmp);
            switch (tmp) {

                case "Arl":
                    mostrarARL = modelo.getMostrar();
                    break;
                case "Celular":
                    mostrarCelular = modelo.getMostrar();
                    break;
                case "Email":
                    mostrarEmail = modelo.getMostrar();
                    break;
                case "Direccion":
                    mostrarDireccion = modelo.getMostrar();
                    break;
                case "Eps":
                    mostrarEPS = modelo.getMostrar();
                    break;
                case "Fechanacimiento":
                    mostrarnacimiento = modelo.getMostrar();
                    break;
                case "VigenciaSS":
                    mostrarFecha_vigencia_ARL = modelo.getMostrar();
                    mostrarFecha_vigencia_EPS = modelo.getMostrar();
                    break;
                case "Departamento":
                    mostrarDepartamento = modelo.getMostrar();
                    break;
                case "Id_externo":
                    mostrarIdExterno = modelo.getMostrar();
                    //limpiarIdExterno = mostrarIdExterno;
                    break;
                case "Municipio":
                    mostrarMunicipio = modelo.getMostrar();
                    break;
                case "Pais":
                    mostrarPais = modelo.getMostrar();
                    break;
                case "Sexo":
                    mostrarSexo = modelo.getMostrar();
                    break;
                case "Telefono":
                    mostrarTelefono = modelo.getMostrar();
                    break;
                case "Rh":
                    mostrarTipo_sanguineo = modelo.getMostrar();
                    break;
                case "Funcionario":
                    mostrarFuncionario = modelo.getMostrar();
                    break;
                case "Empresa":
                    mostrarEmpresa = modelo.getMostrar();
                    break;
                case "Personacontacto":
                    mostrarPersonaContacto = modelo.getMostrar();
                    break;
                case "Telpersonacontacto":
                    mostrarTelefonoContacto = modelo.getMostrar();
                    break;

            }
        }
    }
    //</editor-fold>

    // <editor-fold desc="Disable no editable fields" defaultstate="collapsed">
    public void disableNoEditableFields(boolean enable) {
        disableNoEditableField = enable;
    }
    //</editor-fold>

    // <editor-fold desc="Verify Dates" defaultstate="collapsed">
    public boolean verifyDatesPerson(Personas selected) {
        boolean fechaSS = true;
        //Determina si se esta mostrando el campo seleccionado.
        if (mostrarFecha_vigencia_ARL == false || mostrarFecha_vigencia_EPS == false) {
            return true;
        }
        //Determina si viene nulo dicho parametro.
        if (selected.getFechaVigenciaSS() == null) {
            JsfUtil.addErrorMessage("La persona no tiene asignada fecha de vigencia de Seguridad Social");
            return false;
        }
        if (selected.getFechaVigenciaSS().before(new Date())) {
            fechaSS = false;
            selected.setFechaVigenciaSS(null);
            JsfUtil.addErrorMessage("Fecha de vigencia de la seguridad social esta vencida");
        }
        return fechaSS;
    }
    //</editor-fold>

    // <editor-fold desc="valueChangeHandlerOriginEnterprise" defaultstate="collapsed">
    public void valueChangeHandlerOriginEnterprise(ValueChangeEvent changeEvent) {
        EmpresaOrigen selectedOriginEnterprise = (EmpresaOrigen) changeEvent.getNewValue();
        if (selectedOriginEnterprise.getIdEmpresaOrigen() != null && selectedOriginEnterprise.getIdEmpresaOrigen() == (Constants.ORIGIN_ENTERPRISE_OTHER)) {
            disableOtherEnterprise = false;
            return;
        }
        disableOtherEnterprise = true;
    }
    //</editor-fold>

    public String entryPerson() {

        int resutlMethot = personasBusinessEntry.entryPerson(nombreEmpresa, persona, personaSucursal);
        personaSucursal = personasBusinessEntry.getPersonaSucusal();
        movPersona = personasBusinessEntry.getMovPersonas();
        switch (resutlMethot) {
            case Constants.OK:
                PhotoUtils.almacenarFoto(Constants.OBJECT_PERSON, imagen, persona.getIdPersona());
                JsfUtil.addSuccessMessage("Registro exitoso");
                PhotoUtils.almacenarFoto(Constants.OBJECT_PERSON, imagen, persona.getIdPersona());
                return Navigation.PAGE_SELECT_ENTRY;
            case Constants.UNKNOWN_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo realizar el registro. Contacte al servicio tecnico");
                return Navigation.PAGE_PERSON_REGISTER;
            case Constants.PERSISTANCE_EXCEPTION:
                JsfUtil.addErrorMessage("No se pudo crear el registro de persona. Contacte al servicio tecnico");
                return Navigation.PAGE_PERSON_REGISTER;
            case Constants.OBJECT_BLOCK:
                JsfUtil.addErrorMessage("La persona que intenta ingresar esta bloqueado.");
                return Navigation.PAGE_PERSON_REGISTER;
        }
        JsfUtil.addErrorMessage("Algo salio mal. Contacte al servicio tecnico");
        return Navigation.PAGE_PERSON_REGISTER;
    }

    public void oncapture(CaptureEvent captureEvent) {
        imagen = captureEvent.getData();
    }

}
