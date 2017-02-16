/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.esdairim.applicationcore.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ESDAIRI
 */
@Entity
public class ProvidedInterface implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne
    private Component component;
    @OneToMany(mappedBy = "providedInterface")
    private List<ProvidedInterfaceItem> providedInterfaceItems;

    public List<ProvidedInterfaceItem> getProvidedInterfaceItems() {
        return providedInterfaceItems;
    }

    public void setProvidedInterfaceItems(List<ProvidedInterfaceItem> providedInterfaceItems) {
        this.providedInterfaceItems = providedInterfaceItems;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProvidedInterface)) {
            return false;
        }
        ProvidedInterface other = (ProvidedInterface) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.ProvidedInterface[ id=" + id + " ]";
    }

}
