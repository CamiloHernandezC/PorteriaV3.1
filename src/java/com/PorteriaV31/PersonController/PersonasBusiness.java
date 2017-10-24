/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.PersonController;

import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Entities.Personas;
import com.PorteriaV31.Entities.PersonasSucursal;
import com.PorteriaV31.Entities.TiposDocumento;
import com.PorteriaV31.Facade.MovPersonasFacade;
import com.PorteriaV31.Facade.PersonasFacade;
import com.PorteriaV31.Facade.PersonasSucursalFacade;
import com.PorteriaV31.Querys.Querys;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Result;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;

/**
 *
 * @author chernandez
 */
public class PersonasBusiness {
    
    @EJB
    protected PersonasFacade personasFacade;
    @EJB
    protected PersonasSucursalFacade personasSucursalFacade;
    @EJB
    protected MovPersonasFacade movPersonasFacade;
    
    protected PersonasSucursal personaSucusal;
    
    protected MovPersonas movPersonas;

    public MovPersonas getMovPersonas() {
        return movPersonas;
    }

    public void setMovPersonas(MovPersonas movPersonas) {
        this.movPersonas = movPersonas;
    }
    
    public PersonasSucursal getPersonaSucusal() {
        return personaSucusal;
    }

    public void setPersonaSucusal(PersonasSucursal personaSucusal) {
        this.personaSucusal = personaSucusal;
    }
    
    public Result findPersonByDocument(String docType, String docNumber){
        
        StringBuilder squery = new StringBuilder();
        squery.append(Querys.PERSONA_CLI_ALL);
        squery.append("WHERE");
        squery.append(Querys.PERSONA_CLI_DOC_TYPE);
        squery.append(docType);
        squery.append("' AND");
        squery.append(Querys.PERSONA_CLI_DOC_NUMBER);
        squery.append(docNumber);
        squery.append("'");
        
        return personasFacade.findByQuery(squery.toString(), true);
    }
    
     private Result findPersonByIdExterno(String code) {
       StringBuilder squery = new StringBuilder(Querys.PERSONAS_SUCURSAL_CLI_ALL);
       squery.append("WHERE");
       squery.append(Querys.PERSONAS_SUCURSAL_ID_EXTERNO);
       squery.append(code);
       squery.append("'");
       return personasSucursalFacade.findByQuery(squery.toString(), false);//ID EXTERNO MUST BE UNIQUE FOR NOW
    }

    public boolean verifyBlockedPerson(Personas selected) {
        if (Objects.equals(selected.getEstado().getIdEstado(), Constants.STATUS_BLOCKED)) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockedDialog').show();");
            return true;
        }
        return false;
    }

    public Result findByCodeReader(String code,Personas persona) {
        if (code.startsWith("C,")) {//ID CARD (CEDULA)
            String[] separatedWords = JsfUtil.separateWords(code);
            if (separatedWords != null) {
                //Se debe asignar el tipo de documento y número de documento para poder buscar
                persona.setTipoDocumento(new TiposDocumento(Constants.DOCUMENT_TYPE_CEDULA));//Se asigna el tipo de documento como cedula
                persona.setNumeroDocumento(separatedWords[0]);//Se le asigna el numero de cedula que fue leido por el lector de cedulas
                
                //<editor-fold desc="Assign selected to info in id card" defaultstate="collapsed">
                persona.setApellido1(separatedWords[1]);
                persona.setApellido2(separatedWords[2]);
                persona.setNombre1(separatedWords[3]);
                persona.setNombre2(separatedWords[4]);
                persona.setSexo(separatedWords[5].equals("M"));
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                try {
                    Date birthDate = formatter.parse(separatedWords[6]);
                    persona.setFechaNacimiento(birthDate);
                } catch (ParseException ex) {
                    System.out.println(Constants.MESSAGE_DATE_FORMAT_EXCEPTION);
                }
                String RH = "¡".equals(separatedWords[7].substring(1)) ? "+" : "-";
                persona.setRh(separatedWords[7].substring(0, 1) + RH);
                //</editor-fold>
                
                return findPersonByDocument(String.valueOf(persona.getTipoDocumento().getTipoDocumento()), persona.getNumeroDocumento());
                
            }
        }
        if (code.startsWith("B,")) {//BAR CODE
            return findPersonByIdExterno(code.substring(2));
        }
        return new Result(null, Constants.UNKNOWN_EXCEPTION);//Cuando el formato no coincide con los soportados
    }

    public Result verifyEntry(Personas persona) {

        StringBuilder sQuery = new StringBuilder(Querys.MOV_PERSONA_CLI_ALL);
        sQuery.append("WHERE");
        sQuery.append(Querys.MOV_PERSONA_CLI_PERSONA);
        sQuery.append(persona.getIdPersona());
        sQuery.append("' AND");
        sQuery.append(Querys.MOV_PERSONA_CLI_MOMENTO_SALIDA_NULL);
        
        return movPersonasFacade.findByQueryArray(sQuery.toString());
    }

    protected MovPersonas prepareEntityToCreate(PersonasSucursal personaSucursal) {
        
        if(movPersonas==null){
            movPersonas = new MovPersonas();
        }
        Date actualDate = new Date();
        movPersonas.setPersonasSucursal(personaSucursal);
        movPersonas.setArea(personaSucursal.getArea());
        movPersonas.setMomentoEntrada(actualDate);
        movPersonas.setFechaEntrada(actualDate);
        movPersonas.setHoraEntrada(actualDate);
        movPersonas.setSalidaForzosa(false);
        
        return movPersonas;
    }

    protected PersonasSucursal searchPersonaSucursal(PersonasSucursal personaSucursal,Personas persona) {
        StringBuilder sQuery = new StringBuilder(Querys.PERSONAS_SUCURSAL_CLI_ALL);
        sQuery.append(" Where ");
        sQuery.append(Querys.PERSONAS_SUCURSAL_CLI_PERSONA);
        sQuery.append(persona.getIdPersona());
        sQuery.append("' and ");
        sQuery.append(Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL);
        sQuery.append(personaSucursal.getSucursales().getIdSucursal());
        sQuery.append("'");

        personaSucursal = (PersonasSucursal) personasSucursalFacade.findByQuery(sQuery.toString(), false).result;
        return personaSucursal;
    }

   
    
}
