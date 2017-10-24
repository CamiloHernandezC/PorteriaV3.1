/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Convertes;

import com.PorteriaV31.Entities.Theme;
import com.PorteriaV31.Facade.ThemeFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author chernandez
 */
@Named
@RequestScoped
public class ThemeConverter {

    @EJB
    private ThemeFacade ejbFacade;

    public ThemeConverter() {
    }

    public ThemeFacade getEjbFacade() {
        return ejbFacade;
    }
    
    @FacesConverter(forClass = Theme.class)
    public static class ThemeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ThemeConverter converter =  (ThemeConverter) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "themeConverter");
            return converter.getEjbFacade().find(getKey(value));
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
            if (object instanceof Theme) {
                Theme o = (Theme) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Theme.class.getName()});
                return null;
            }
        }

    }


}
