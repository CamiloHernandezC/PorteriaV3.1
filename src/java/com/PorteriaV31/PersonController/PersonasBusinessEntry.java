/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.Activ.bussiness.EmpresaOrigenController;
import com.PorteriaV31.Entities.EmpresaOrigen;
import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Facade.EmpresaOrigenFacade;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.Result;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chernandez
 */
@Stateless
public class PersonasBusinessEntry extends PersonasBusiness{
    
    @EJB
    EmpresaOrigenController empresaController;
    
    @EJB
    EmpresaOrigenFacade empFacade;

    public int entryPerson(String nombreEmpresa,Personas persona, PersonasSucursal personaSucursal) {
       
        boolean existPerson = persona.getIdPersona() != null;
        
        //<editor-fold desc="CREACION EMPRESA" defaultstate="collapsed">
        if (persona.getEmpresaOrigen() != null && persona.getEmpresaOrigen().getIdEmpresaOrigen() == 1) {
            //Determina la existencia de la empresa cuando se ingresa desde formulario
            EmpresaOrigen empresa = empresaController.buscarEmpresaNombre(nombreEmpresa);
            if (empresa != null) {
                persona.setEmpresaOrigen(empresa);
            } else {
                empresa = new EmpresaOrigen();
                empresa.setNombre1(nombreEmpresa);
                if (empFacade.create(empresa).errorCode != Constants.OK) {
                    return Constants.PERSISTANCE_EXCEPTION;
                }
                persona.setEmpresaOrigen(empresa);
            }
        }
        //</editor-fold>
        
        if(!existPerson){
            //Create a person in person table.
            if (personasFacade.create(persona).errorCode != Constants.OK) {
                //It can not create.
                return Constants.PERSISTANCE_EXCEPTION;
            }
            //Assing Sucursal to Vehicles Sucursal from Person Sucursal.        
            personaSucursal.setPersonas(persona);
            //Create a person Sucursal in PersonSucursal table.
            if (personasSucursalFacade.create(personaSucursal).errorCode != Constants.OK) {
                //It can not create
                return Constants.PERSISTANCE_EXCEPTION;
            }
            //Create the movment into the table movVehiculos. 
            if (!recordEntryMovement(persona, personaSucursal)) {
                return Constants.PERSISTANCE_EXCEPTION;
            }
            this.personaSucusal=personaSucursal;
            return Constants.OK;
        }
        //Exist person in table personaSucursal.
        PersonasSucursal personaSuc = searchPersonaSucursal(personaSucursal,persona);
        if (personaSuc == null) {
            //Assing person to person Sucursal from Person Sucursal.
            personaSucursal.setPersonas(persona);
            Result result = personasSucursalFacade.create(personaSucursal);
            if (result.errorCode != Constants.OK) {
                //It can not create
                return Constants.PERSISTANCE_EXCEPTION;
            }
            personaSuc = (PersonasSucursal) result.result;
        }
        personaSucursal=personaSuc;
        if (personaSucursal.getEstado().getIdEstado() == Constants.STATUS_BLOCKED) {
            //Vehicle block in sucursal person table.
            return Constants.OBJECT_BLOCK;
        }
        if (!recordEntryMovement(persona, personaSucursal)) {
            return Constants.PERSISTANCE_EXCEPTION;
        }
        //Assing local personaSucursal to global variable to use into bean of the form
        this.personaSucusal=personaSucursal;
        //Update person and sucursalPerson.
        personasFacade.update(persona);
        personasSucursalFacade.update(personaSucursal);
        return Constants.OK;
    }

    public boolean recordEntryMovement(Personas persona, PersonasSucursal personaSucursal) {
        List<MovPersonas> movPersonList = (List<MovPersonas>) verifyEntry(persona).result;
        if (!movPersonList.isEmpty()) {
            recordForcedOut(movPersonList);
        }
        return recordEntry(personaSucursal) == Constants.OK;
    }
    
    private void recordForcedOut(List<MovPersonas> movPersonList) {
        for(MovPersonas mov: movPersonList){
            mov.setFechaSalida(mov.getFechaEntrada());
            mov.setHoraSalida(mov.getHoraEntrada());
            mov.setMomentoSalida(mov.getMomentoEntrada());
            mov.setSalidaForzosa(true);
            movPersonasFacade.update(mov);
        }
    }

    private int recordEntry(PersonasSucursal personaSucursal) {
        
        movPersonas = prepareEntityToCreate(personaSucursal);
        return movPersonasFacade.create(movPersonas).errorCode;
    }
}
