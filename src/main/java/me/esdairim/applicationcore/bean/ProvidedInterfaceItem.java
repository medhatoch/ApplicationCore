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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 *
 * @author ESDAIRI
 */
@Entity
public class ProvidedInterfaceItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne
    private Output output;
    @OneToMany(mappedBy = "providedInterfaceItem")
    private List<Input> inputs;
    @ManyToOne
    private ProvidedInterface providedInterface;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProvidedInterface getProvidedInterface() {
        return providedInterface;
    }

    public void setProvidedInterface(ProvidedInterface providedInterface) {
        this.providedInterface = providedInterface;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
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
        if (!(object instanceof ProvidedInterfaceItem)) {
            return false;
        }
        ProvidedInterfaceItem other = (ProvidedInterfaceItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.ProvidedInterfaceItem[ id=" + id + " ]";
    }

}
