package com.PorteriaV31.Convertes;


import com.PorteriaV31.Entities.Notificaciones;
import com.PorteriaV31.Facade.NotificacionesFacade;
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
public class NotificacionesConverter {

    @EJB
    private NotificacionesFacade ejbFacade;
    
    public NotificacionesConverter() {
    }
    
    public Notificaciones getNotificaciones(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Notificaciones.class)
    public static class NotificacionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotificacionesConverter controller = (NotificacionesConverter) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notificacionesConverter");
            return controller.getNotificaciones(getKey(value));
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
            if (object instanceof Notificaciones) {
                Notificaciones o = (Notificaciones) object;
                return getStringKey(o.getIdNotificacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Notificaciones.class.getName()});
                return null;
            }
        }

    }
    
}
