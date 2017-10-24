/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Activ.bussiness;

import com.PorteriaV31.Entities.Arl;
import com.PorteriaV31.Entities.Departamentos;
import com.PorteriaV31.Entities.Entidades;
import com.PorteriaV31.Entities.Eps;
import com.PorteriaV31.Entities.Paises;
import com.PorteriaV31.Entities.TiposDocumento;
import com.PorteriaV31.Facade.ArlFacade;
import com.PorteriaV31.Facade.DepartamentosFacade;
import com.PorteriaV31.Facade.EntidadesFacade;
import com.PorteriaV31.Facade.EpsFacade;
import com.PorteriaV31.Facade.PaisesFacade;
import com.PorteriaV31.Facade.TiposDocumentoFacade;
import com.PorteriaV31.Querys.Querys;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

/**
 *
 * @author chernandez
 */
@Singleton
//@Startup
public class GeneralDataControl {
    @EJB
    private TiposDocumentoFacade ejbTiposDocumento;
    private List<TiposDocumento> tiposDocumento;
    
    @EJB
    private EntidadesFacade ejbEntidadesFacade;
    private List<Entidades> entidades;
    
    @EJB
    private PaisesFacade paisesFacade;
    private List<Paises> countries;
    
    @EJB
    private DepartamentosFacade departamentosFacade;
    private List<Departamentos> departments;
    
    @EJB
    private EpsFacade epsFacade;
    private List<Eps> eps;
    private int lastEpsID;
    
    @EJB
    private ArlFacade arlFacade;
    private List<Arl> arl;
    private int lastArlID;
    
    @PostConstruct
    public void init(){
        getTiposDocumento();
        getEntidades();
        getCountries();
        getDepartments();
    }

    @Lock(LockType.READ)
    public List<TiposDocumento> getTiposDocumento() {
        if(tiposDocumento==null){
            tiposDocumento = ejbTiposDocumento.findAll();
        }
        return tiposDocumento;
    }
    
    @Lock(LockType.READ)
    public List<Entidades> getEntidades() {
        if(entidades==null){
            entidades = ejbEntidadesFacade.findAll();
        }
        return entidades;
    }
    
    @Lock(LockType.READ)
    public List<Paises> getCountries(){
        if(countries==null){
            countries = paisesFacade.findAll();
        }
        return countries;
    }

    @Lock(LockType.READ)
    public List<Departamentos> getDepartments() {
        if(departments==null){
            departments = departamentosFacade.findAll();
        }
        return departments;
    }
    
    @Lock(LockType.READ)
    public List<Eps> getEps(){
        if(eps==null){
            eps = epsFacade.findAll();
        }
        else{
            Eps lastEps = (Eps) epsFacade.findByQuery(Querys.EPS_PRIMARY_KEY, true).result;
            if(lastEpsID != lastEps.getEps()){
                lastEpsID = lastEps.getEps();
                eps = epsFacade.findAll();
            }
        }
        return eps;
    }

    @Lock(LockType.READ)
    public List<Arl> getArl() {
        if(arl==null){
            arl = arlFacade.findAll();
        }
        else{
            Arl lastArl = (Arl) (arlFacade.findByQuery(Querys.ARL_PRIMARY_KEY, true).result);
            if(lastArlID != lastArl.getArl()){
                lastArlID = lastArl.getArl();
                arl = arlFacade.findAll();
            }
        }
        return arl;
    }

}
