package com.PorteriaV31.Convertes;


import com.PorteriaV31.Entities.EmpresaOrigen;
import com.PorteriaV31.Facade.EmpresaOrigenFacade;
import java.util.List;
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
public class EmpresaOrigenConverter {

    @EJB
    private EmpresaOrigenFacade ejbFacade;
    
    public EmpresaOrigenConverter() {
    }
   
    public EmpresaOrigen getEmpresaOrigen(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    @FacesConverter(forClass = EmpresaOrigen.class)
    public static class EmpresaOrigenControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmpresaOrigenConverter controller = (EmpresaOrigenConverter) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "empresaOrigenConverter");
            return controller.getEmpresaOrigen(getKey(value));
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
            if (object instanceof EmpresaOrigen) {
                EmpresaOrigen o = (EmpresaOrigen) object;
                return getStringKey(o.getIdEmpresaOrigen());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EmpresaOrigen.class.getName()});
                return null;
            }
        }

    }
}
