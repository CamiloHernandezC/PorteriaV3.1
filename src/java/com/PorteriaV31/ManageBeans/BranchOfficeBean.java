/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.ManageBeans;



import com.PorteriaV31.Entities.PorteriasSucursal;
import com.PorteriaV31.Entities.Sucursales;
import com.PorteriaV31.Entities.Usuarios;
import com.PorteriaV31.Facade.AccesoUsuarioFacade;
import com.PorteriaV31.Querys.Querys;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.JsfUtil;
import com.PorteriaV31.Utils.Result;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author MAURICIO
 */
@Named
@SessionScoped
public class BranchOfficeBean implements Serializable{
    
    @EJB
    private AccesoUsuarioFacade ejbFacade;
    private ArrayList<Sucursales> branchOffices = null;
    private Sucursales selectedBranchOffice = null;
    
    /**
     * Creates a new instance of MenuController
     */
    public BranchOfficeBean() {
    }

    public boolean isShowBranchOffice() {
        loadBranchOffices();
        if(branchOffices==null || branchOffices.isEmpty()){
            return false;
        }
        if(branchOffices.size()==1){
            selectedBranchOffice = branchOffices.get(0);
            return false;
        }
        return true;
    }

    public Sucursales getSelectedBranchOffice() {
        return selectedBranchOffice;
    }

    public void setSelectedBranchOffice(Sucursales selectedBranchOffice) {
        this.selectedBranchOffice = selectedBranchOffice;
    }

    public ArrayList<Sucursales> getBranchOffices() {
        return branchOffices;
    }

    public void setBranchOffices(ArrayList<Sucursales> branchOffices) {
        this.branchOffices = branchOffices;
    }
    
    public void loadBranchOffices(){
        branchOffices = new ArrayList<>();
        Usuarios user = JsfUtil.getSessionUser();
        String squery = Querys.PORTERIA_SUCURSAL_CLI_PORTERIA+"1'";//TODO ASSIGN REAL ENTRY HERE
        Result result = ejbFacade.findByQueryArray(squery);
        if(result.errorCode==Constants.OK){
            List<PorteriasSucursal> list = (List<PorteriasSucursal>) result.result;
            list.stream().forEach((a) -> {
                branchOffices.add(a.getSucursales());
            });
        }
    }
}
