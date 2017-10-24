/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Activ.bussiness;

import com.PorteriaV31.Entities.AreasEmpresa;
import com.PorteriaV31.Facade.AreasEmpresaFacade;
import com.PorteriaV31.Querys.Querys;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chernandez
 */
@Stateless
public class AreasControl {
    
    @EJB
    private AreasEmpresaFacade areasFacade;
    
    public List<AreasEmpresa> getAreasByBranchOffice(int idBranchOffice) {
        String squery = Querys.AREAS_EMPRESA_ALL + " WHERE" + Querys.AREAS_EMPRESA_SUCURSAL + idBranchOffice + "'";
        return (List<AreasEmpresa>) areasFacade.findByQueryArray(squery).result;
    }
}
