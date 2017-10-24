/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Activ.bussiness;

import com.PorteriaV31.Entities.Municipios;
import com.PorteriaV31.Facade.MunicipiosFacade;
import com.PorteriaV31.Querys.Querys;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chernandez
 */
@Stateless
public class MunicipalityControl {
    
    @EJB
    private MunicipiosFacade municipiosFacade;
    
    public List<Municipios> getmunicipalitiesByDepartment(int idDepartment){
        StringBuilder sb = new StringBuilder();
        sb.append(Querys.MUNICIPIOS_CLI_DEPARTAMENTO);
        sb.append(idDepartment);
        sb.append("'");
        return (List<Municipios>) municipiosFacade.findByQueryArray(sb.toString()).result;
    }
}
