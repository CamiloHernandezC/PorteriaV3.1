/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.ManageBeans;

import com.Activ.bussiness.AreasControl;
import com.Activ.bussiness.GeneralDataControl;
import com.Activ.bussiness.MunicipalityControl;
import com.PorteriaV31.Entities.AreasEmpresa;
import com.PorteriaV31.Entities.Arl;
import com.PorteriaV31.Entities.Departamentos;
import com.PorteriaV31.Entities.EmpresaOrigen;
import com.PorteriaV31.Entities.Entidades;
import com.PorteriaV31.Entities.Eps;
import com.PorteriaV31.Entities.Municipios;
import com.PorteriaV31.Entities.Paises;
import com.PorteriaV31.Entities.TiposDocumento;
import com.PorteriaV31.Facade.EmpresaOrigenFacade;
import com.PorteriaV31.Facade.EntidadesFacade;
import com.PorteriaV31.Querys.Querys;
import com.PorteriaV31.Utils.Constants;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**s
 *
 * @author chernandez
 */
@Named
@ApplicationScoped
public class GeneralViewBean {
    
    @EJB
    private GeneralDataControl generalDataControl;  
    @EJB
    private AreasControl areasControl;
    @EJB
    private MunicipalityControl municipalityControl;
    @EJB
    private EmpresaOrigenFacade empresaFacade;
    
    @EJB
    private EntidadesFacade ejbEntidadesFacade;
    private List<Entidades> vehiclesTypes;

    @Lock(LockType.READ)
    public List<Entidades> getVehiclesTypes() {
        if(vehiclesTypes==null){
            StringBuilder sb = new StringBuilder();
            sb.append(Querys.ENTIDADES_ALL);
            sb.append(" WHERE");
            sb.append(Querys.ENTIDADES_CATEGORIA);
            sb.append(Constants.CATEGORY_VEHICLE);
            sb.append("'");
            vehiclesTypes = (List<Entidades>) ejbEntidadesFacade.findByQueryArray(sb.toString()).result;
        }
        return vehiclesTypes;
    }
    
    public List<TiposDocumento> getTiposDocumento() {
        return generalDataControl.getTiposDocumento();
    }
    
    public List<Entidades> getEntidades() {
        return generalDataControl.getEntidades();
    }
    
    public List<AreasEmpresa> getAreasBySelectedBranchOffice(int branchOffice){
        return areasControl.getAreasByBranchOffice(branchOffice);
    }
    
    public List<Paises> getCountries(){
        return generalDataControl.getCountries();
    }
    
    public List<Departamentos> getDepartments(){
        return generalDataControl.getDepartments();
    }
    
    public List<Municipios> getmunicipalitiesByDepartment(int idDepartment){
        return municipalityControl.getmunicipalitiesByDepartment(idDepartment);
    }
    
    public List<Eps> getEps(){
        return generalDataControl.getEps();
    }
    
    public List<Arl> getArl(){
        return generalDataControl.getArl();
    }
    
    public List<EmpresaOrigen> getEnterprises(){
        return empresaFacade.findAll();
    }
    
}
