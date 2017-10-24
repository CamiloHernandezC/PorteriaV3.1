package com.PorteriaV31.Login;

import com.PorteriaV31.Entities.Theme;
import com.PorteriaV31.Facade.ThemeFacade;
import com.PorteriaV31.Facade.UsuariosFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("temasBean")
@SessionScoped
public class TemasBean implements Serializable {

    private List<Theme> items = null;
    private Theme selected;
    
    @EJB
    private ThemeFacade ejbFacade;
    
    @EJB
    private UsuariosFacade usuariosFacade;
    
    public TemasBean() {
    }
    
    public Theme getSelected() {
        if(selected==null){
            selected = ejbFacade.find(14);
        }
        return selected;
    }
    
    public void reset(){
        selected = ejbFacade.find(14);
        usuariosFacade.saveTheme(selected);
    }

    public void setSelected(Theme selected) {
        this.selected = selected;
        usuariosFacade.saveTheme(selected);
    }
    
    public void setSelectedLogin(Theme selected) {
        this.selected = selected;
    }

    private ThemeFacade getFacade() {
        return ejbFacade;
    }

    public List<Theme> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public Theme getTheme(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Theme> cargarTemas() {
        List<Theme> lista = ejbFacade.findAll();
        return lista;
    }

}
