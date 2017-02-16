package me.esdairim.applicationcore.controller;

import me.esdairim.applicationcore.bean.ProvidedInterfaceItem;
import me.esdairim.applicationcore.controller.util.JsfUtil;
import me.esdairim.applicationcore.controller.util.JsfUtil.PersistAction;
import me.esdairim.applicationcore.service.ProvidedInterfaceItemFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("providedInterfaceItemController")
@SessionScoped
public class ProvidedInterfaceItemController implements Serializable {

    @EJB
    private me.esdairim.applicationcore.service.ProvidedInterfaceItemFacade ejbFacade;
    private List<ProvidedInterfaceItem> items = null;
    private ProvidedInterfaceItem selected;

    public ProvidedInterfaceItemController() {
    }

    public ProvidedInterfaceItem getSelected() {
        return selected;
    }

    public void setSelected(ProvidedInterfaceItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProvidedInterfaceItemFacade getFacade() {
        return ejbFacade;
    }

    public ProvidedInterfaceItem prepareCreate() {
        selected = new ProvidedInterfaceItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProvidedInterfaceItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProvidedInterfaceItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProvidedInterfaceItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ProvidedInterfaceItem> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ProvidedInterfaceItem getProvidedInterfaceItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ProvidedInterfaceItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ProvidedInterfaceItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ProvidedInterfaceItem.class)
    public static class ProvidedInterfaceItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProvidedInterfaceItemController controller = (ProvidedInterfaceItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "providedInterfaceItemController");
            return controller.getProvidedInterfaceItem(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProvidedInterfaceItem) {
                ProvidedInterfaceItem o = (ProvidedInterfaceItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProvidedInterfaceItem.class.getName()});
                return null;
            }
        }

    }

}
