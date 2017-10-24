package com.PorteriaV31.Convertes;


import com.PorteriaV31.Entities.MovDocumentos;
import com.PorteriaV31.Facade.MovDocumentosFacade;
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
public class MovDocumentosConverter {

    @EJB
    private MovDocumentosFacade ejbFacade;
    
    public MovDocumentosConverter() {
    }

    public MovDocumentos getMovDocumentos(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MovDocumentos.class)
    public static class MovDocumentosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovDocumentosConverter controller = (MovDocumentosConverter) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movDocumentosConverter");
            return controller.getMovDocumentos(getKey(value));
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
            if (object instanceof MovDocumentos) {
                MovDocumentos o = (MovDocumentos) object;
                return getStringKey(o.getIdMovDocumento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovDocumentos.class.getName()});
                return null;
            }
        }

    }

}
