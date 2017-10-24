package com.PorteriaV31.Convertes;


import com.PorteriaV31.Entities.MovObjetos;
import com.PorteriaV31.Facade.MovObjetosFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named
@RequestScoped
public class MovObjetosConverter{

    @EJB
    private MovObjetosFacade ejbFacade;
    
    public MovObjetosConverter() {
    }

    public MovObjetos getMovObjetos(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MovObjetos.class)
    public static class MovObjetosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovObjetosConverter controller = (MovObjetosConverter) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movObjetosConverter");
            return controller.getMovObjetos(getKey(value));
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
            if (object instanceof MovObjetos) {
                MovObjetos o = (MovObjetos) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovObjetos.class.getName()});
                return null;
            }
        }

    }

}
