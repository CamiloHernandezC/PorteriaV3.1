/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Activ.bussiness;

import com.PorteriaV31.Entities.EmpresaOrigen;
import com.PorteriaV31.Facade.EmpresaOrigenFacade;
import com.PorteriaV31.Querys.Querys;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author a.morales
 */

@Stateless
public class EmpresaOrigenController{

  
    @EJB
    EmpresaOrigenFacade empFacade;
    
    public EmpresaOrigen buscarEmpresaNombre(String nombre) {
        String sQuery = Querys.EMPRESA_ORIGEN_NAME+ nombre +"'";
        EmpresaOrigen empresa =  (EmpresaOrigen) empFacade.findByQuery(sQuery, true).result;
        return empresa;
    }

}
