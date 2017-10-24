/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.ManageBeans;

import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Facade.MovPersonasFacade;
import com.PorteriaV31.Querys.Querys;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author amorales
 */
@Named
@ViewScoped//Every change of page it will refresh last movements
public class MinutaBean implements Serializable{
    
    @EJB
    private MovPersonasFacade movFacade;
    private List<MovPersonas> lastMovements;
    private Date dateToSearch;
    private List<MovPersonas> foundMovements;

    public List<MovPersonas> getFoundMovements() {
        return foundMovements;
    }

    public Date getDateToSearch() {
        return dateToSearch;
    }

    public void setDateToSearch(Date dateToSearch) {
        this.dateToSearch = dateToSearch;
    }

    public List<MovPersonas> getLastMovements() {
        return lastMovements;
    }
    
    @PostConstruct
    public void findLastMovements() {
        StringBuilder sQuery = new StringBuilder(Querys.MOV_PERSONA_CLI_ALL);
        //TODO Find by entry.
        sQuery.append(" ORDER BY a.idMovPersona DESC");
        lastMovements = (List<MovPersonas>) movFacade.findByQueryArray(sQuery.toString(),15).result;
    }
    
    public void findMovementsByDate(){
        StringBuilder sb = new StringBuilder();
        sb.append(Querys.MOV_PERSONA_CLI_ALL);sb.append("WHERE");sb.append(Querys.MOV_PERSONA_CLI_ENTRY_DATE);
        sb.append(new java.sql.Date(dateToSearch.getTime()));sb.append("%'");
        foundMovements = (List<MovPersonas>) movFacade.findByQueryArray(sb.toString()).result;
    }
    
}
