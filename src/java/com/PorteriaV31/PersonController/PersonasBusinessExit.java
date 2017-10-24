/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Querys.Querys;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.Result;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author amorales
 */
@Stateless
public class PersonasBusinessExit extends PersonasBusiness{
    
    MovPersonas movPersona;

    public MovPersonas getMovPersona() {
        return movPersona;
    }

    public void setMovPersona(MovPersonas movPersona) {
        this.movPersona = movPersona;
    }

    public int exitPerson(Personas persona, PersonasSucursal personaSucursal) {
        if(!recordExitMovement(persona,personaSucursal)){
            return Constants.PERSISTANCE_EXCEPTION;
        }
        return Constants.OK;
    }

    private boolean recordExitMovement(Personas persona, PersonasSucursal personaSucursal) {
        List<MovPersonas> movPersonList = (List<MovPersonas>) verifyEntry(persona).result;
        if (movPersonList.isEmpty()) {
            return recordForcedIn(personaSucursal);
        }
        movPersona=movPersonList.get(0);
        return recordExit() == Constants.OK;
    }
    
    private boolean recordForcedIn(PersonasSucursal personasSucursal) {
        if(movPersona==null){
            movPersona = new MovPersonas();            
        }
       movPersona = prepareEntityToCreate(personasSucursal);
       movPersona.setFechaSalida(movPersona.getFechaEntrada());
       movPersona.setHoraSalida(movPersona.getHoraEntrada());
       movPersona.setIngresoForzado(true);
       return movPersonasFacade.create(movPersona).errorCode==Constants.OK;
    }

    private int recordExit() {
       Date actualDate = new Date();
       movPersona.setFechaSalida(actualDate);
       movPersona.setHoraSalida(actualDate);
       movPersona.setMomentoSalida(actualDate);
       return movPersonasFacade.update(movPersona).errorCode;
    }

    public void loadSpecificPersonByEntry(Personas persona) {
        MovPersonas movPersonas = (MovPersonas) lastEntry(persona.getIdPersona()).result;
        personaSucusal = searchPersonaSucursal(movPersonas.getPersonasSucursal(), persona);
    }
    
    public Result lastEntry(int idPersona) {
       StringBuilder sQuery = new StringBuilder(Querys.MOV_PERSONA_CLI_ALL);
       sQuery.append("WHERE");
       sQuery.append(Querys.MOV_PERSONA_CLI_PERSONA);
       sQuery.append(idPersona);
       sQuery.append("'");
       sQuery.append(Querys.MOV_PERSONA_CLI_ORDER_BY_ID);
       return movPersonasFacade.findByQuery(sQuery.toString(), true);
    }

    public void clean() {
        movPersona=null;
    }
    
}
