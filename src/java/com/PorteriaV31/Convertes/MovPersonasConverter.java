package com.PorteriaV31.Convertes;


import com.PorteriaV31.Entities.MovPersonas;
import com.PorteriaV31.Facade.MovPersonasFacade;
import java.util.List;
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
public class MovPersonasConverter {

    @EJB
    private MovPersonasFacade ejbFacade;
    
    public MovPersonasConverter() {
    }
    
    public MovPersonas getMovPersonas(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MovPersonas.class)
    public static class MovPersonasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovPersonasConverter controller = (MovPersonasConverter) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movPersonasConverter");
            return controller.getMovPersonas(getKey(value));
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
            if (object instanceof MovPersonas) {
                MovPersonas o = (MovPersonas) object;
                return getStringKey(o.getIdMovPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovPersonas.class.getName()});
                return null;
            }
        }

    }

}
