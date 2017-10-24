package com.PorteriaV31.Convertes;

import com.PorteriaV31.Entities.AreasEmpresa;
import com.PorteriaV31.Facade.AreasEmpresaFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@RequestScoped
public class AreasEmpresaConverter{

    @EJB
    private AreasEmpresaFacade ejbFacade;
    
    public AreasEmpresa getAreasEmpresa(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    @FacesConverter(forClass = AreasEmpresa.class)
    public static class AreasEmpresaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreasEmpresaConverter controller = (AreasEmpresaConverter) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areasEmpresaConverter");
            return controller.getAreasEmpresa(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AreasEmpresa) {
                AreasEmpresa o = (AreasEmpresa) object;
                return getStringKey(o.getIdAreaEmpresa());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AreasEmpresa.class.getName()});
                return null;
            }
        }

    }
    
}
