package com.PorteriaV31.VehiclesControllers;

import com.PorteriaV31.Entities.Vehiculos;
import com.PorteriaV31.Entities.ConfigForm;
import com.PorteriaV31.Entities.MovVehiculos;
import com.PorteriaV31.Entities.VehiculosSucursal;
import com.PorteriaV31.Facade.ConfigFormFacade;
import com.PorteriaV31.Utils.Constants;
import com.PorteriaV31.Utils.Navigation;
import com.PorteriaV31.Facade.VehiculosFacade;
import com.PorteriaV31.PersonController.PersonFormEntryBean;
import com.PorteriaV31.PersonController.PersonFormExitBean;
import com.PorteriaV31.Utils.JsfUtil;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


public abstract class VehiclesIOController implements Serializable {

    @EJB
    protected VehiculosFacade ejbFacade;
    
    @EJB
    protected ConfigFormFacade ejbFacadeConfig;
    
    @EJB
    protected BusinessEntryVehicles ejbBusinessEntryVehicles;
    
    @EJB
    protected BusinessExitVehicles ejbBusinessExitVehicles;
    
    @Inject
    protected PersonFormEntryBean personFormEntryBean;
    
    @Inject
    protected PersonFormExitBean personFormExitBean;
    
    //@Inject
    //protected PersonasSucursalController personaSucursalController;
    
    //@Inject
    //protected MovPersonasController movPersonasController;
    
    protected List<Vehiculos> items = null;
    protected VehiculosSucursal selected;
    protected MovVehiculos movVehiculos;
    protected final String Entry = Constants.STRING_ENTRY;
    protected final String Exit = Constants.STRING_EXIT;

    /**
     * Find by plate the vehicle into the requierd table, later show messages de-
     * pend of the value of selected.
     */ 
    protected abstract void buscarPorPlaca();
    
    // <editor-fold desc="Array de config y Booleanos Vehiculos" defaultstate="collapsed">
    protected List<ConfigForm> array;
    
    protected boolean mostrarColor;
    protected boolean mostrarObservacion;
    protected boolean mostrarTipoVehiculo;
    protected boolean mostrarFoto;
    
    protected boolean bloquearCampos =true;
    protected boolean bloquearCampoObservacion =true;
    protected boolean bloquearPlaca =false;

    public boolean isBloquearPlaca() {
        return bloquearPlaca;
    }

    public void setBloquearPlaca(boolean bloquearPlaca) {
        this.bloquearPlaca = bloquearPlaca;
    }

    public boolean isBloquearCampoObservacion() {
        return bloquearCampoObservacion;
    }

    public void setBloquearCampoObservacion(boolean bloquearCampoObservacion) {
        this.bloquearCampoObservacion = bloquearCampoObservacion;
    }
    
    public boolean isBloquearCampos() {
        return bloquearCampos;
    }

    public void setBloquearCampos(boolean bloquearCampos) {
        this.bloquearCampos = bloquearCampos;
    }
    
    public boolean isMostrarColor() {
        return mostrarColor;
    }

    public void setMostrarColor(boolean mostrarColor) {
        this.mostrarColor = mostrarColor;
    }

    public boolean isMostrarObservacion() {
        return mostrarObservacion;
    }

    public void setMostrarObservacion(boolean mostrarObservacion) {
        this.mostrarObservacion = mostrarObservacion;
    }

    public boolean isMostrarTipoVehiculo() {
        return mostrarTipoVehiculo;
    }

    public void setMostrarTipoVehiculo(boolean mostrarTipoVehiculo) {
        this.mostrarTipoVehiculo = mostrarTipoVehiculo;
    }

    public boolean isMostrarFoto() {
        return mostrarFoto;
    }

    public void setMostrarFoto(boolean mostrarFoto) {
        this.mostrarFoto = mostrarFoto;
    }
    //</editor-fold>

    // <editor-fold desc="Getters/Setters" defaultstate="collapsed">

    public String getEntry() {
        return Entry;
    }

    public String getExit() {
        return Exit;
    }
    
    public MovVehiculos getMovVehiculos() {
        if(movVehiculos==null){
            movVehiculos=new MovVehiculos();
        }
        return movVehiculos;
    }

    public void setMovVehiculos(MovVehiculos movVehiculos) {
        this.movVehiculos = movVehiculos;
    }

    public VehiculosSucursal getSelected() {
        if(selected==null){
            selected=new VehiculosSucursal();
            selected.setVehiculos(new Vehiculos());
        }
        return selected;
    }

    public void setSelected(VehiculosSucursal selected) {
        this.selected = selected;
    }

    public List<Vehiculos> getItems() {
        if (items == null) {
            items = ejbFacade.findAll();
        }
        return items;
    }

    public Vehiculos getVehiculos(java.lang.String id) {
        return ejbFacade.find(id);
    }

    public List<Vehiculos> getItemsAvailableSelectMany() {
        return ejbFacade.findAll();
    }
    //</editor-fold>
    
    // <editor-fold desc="Cancel" defaultstate="collapsed">
    /**
     * Cancel depend of view
     * @param event E or S
     * @return Return the page outcome.
     */
    public String cancel(String event) {
        clean();
        if(event.equals(Constants.STRING_ENTRY)){
            return Navigation.PAGE_SELECT_ENTRY;
        }
        if(event.equals(Constants.STRING_EXIT)){
            return Navigation.PAGE_SELECT_EXIT;
        }
        return Navigation.PAGE_INDEX;
    }
    
     protected void clean(){
        selected = null;
        items = null;
    }
     //</editor-fold>
     
    // <editor-fold desc="Load form vehicles" defaultstate="collapsed">
    /**
     * Load the fields to use into the form of the vehiclesEntry.
     */
    @PostConstruct
    public void loadFormVehicles(){
        array = ejbFacadeConfig.showFieldsVehicles();
        if(array.isEmpty() || array == null){
            return;
        }
        for (int i = 0; i < array.size(); i++) {
            ConfigForm modelo = array.get(i);
            String tmp = modelo.getCampo();
            tmp = JsfUtil.quitaEspacios(tmp);
            switch (tmp) {
                case "Color":
                    mostrarColor = modelo.getMostrar();
                    break;
                case "Observacion":
                    mostrarObservacion = modelo.getMostrar();
                    break;
                case "TipoVehiculo":
                    mostrarTipoVehiculo = modelo.getMostrar();
                    break;
                case "Foto":
                    mostrarFoto = modelo.getMostrar();
                    break;
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold desc="Convertidor" defaultstate="collapsed">
    @FacesConverter(forClass = Vehiculos.class)
    public static class VehiculosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VehiclesIOController controller = (VehiclesIOController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vehiculosController");
            return controller.getVehiculos(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Vehiculos) {
                Vehiculos o = (Vehiculos) object;
                return getStringKey(o.getPlaca());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Vehiculos.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
    
    // <editor-fold desc="Reset" defaultstate="collapsed">
    /**
     * Cancel depend of view
     * @param event E or S
     * @return View of Select_Entry/Exit
     */
    public String resetView(String event){
        if(event.equals(Constants.STRING_ENTRY)){
           return Navigation.PAGE_VEHICLE_ENTRY; 
        }
        if(event.equals(Constants.STRING_EXIT)){
           return Navigation.PAGE_VEHICLE_EXIT; 
        }
        return null;
        
    }
    //</editor-fold>
    
}
