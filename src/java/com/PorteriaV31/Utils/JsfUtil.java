package com.PorteriaV31.Utils;


import com.PorteriaV31.Entities.Usuarios;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info: \n"+msg,msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    
    public static Usuarios getSessionUser() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return (Usuarios) httpSession.getAttribute(Constants.SESSION_USER);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
    
    public static String quitaEspacios(String texto) {
        texto= texto.replaceAll(" ", "");
        texto = texto.trim();
        texto = texto.replaceAll("\u00A0", "");
        return texto;
    }
    
    public static void cancel() {
        redirectTo(Navigation.PAGE_INDEX);
    }
    
    public static void cancelToSelectEntry(){
        redirectTo(Navigation.PAGE_SELECT_ENTRY);
    }
    
    public static void cancelToSelectExit(){
        redirectTo(Navigation.PAGE_SELECT_EXIT);
    }

    public static void redirectTo(String page) {
        
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(Navigation.PAGE_REDIRECT_TO+page);
        } catch (Exception e) {
            System.out.println("Exception cancel " + e);
        }
    }
    
    public static void showModal(String nombreModal){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('"+nombreModal+"').show();");
    }
    
    public static void exitModal(String nombreModal){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('"+nombreModal+"').hide();");
    }
    
    public static void addTecnicalErrorMessage() {
        addErrorMessage(BundleUtils.getBundleProperty("Tecnical_Failure"));
    }
    
    public static String[] separateWords(String code) {
        int commaCounter = 0;
        String[] separatedWords = new String[10];
        int oldi = 1;
        for (int i = 2; i < code.length(); i++) {//Start in 2 to avoid "C,"
            char c = code.charAt(i);
            if (c == ',') {
                if (oldi + 1 != i) {
                    separatedWords[commaCounter] = code.substring(oldi + 1, i);
                } else {
                    separatedWords[commaCounter] = "";
                }
                commaCounter++;
                oldi = i;
            }
        }
        if (commaCounter == 9) {
            separatedWords[0] = String.valueOf(Integer.parseInt(separatedWords[0]));//Las cedulas las completa con 0 a la izquierda, esta linea de codigo quita los 0
            return separatedWords;
        }
        return null;
    }
}
