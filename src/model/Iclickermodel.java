/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joe
 */
@Entity
@Table(name = "ICLICKERMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Iclickermodel.findAll", query = "SELECT i FROM Iclickermodel i")
    , @NamedQuery(name = "Iclickermodel.findById", query = "SELECT i FROM Iclickermodel i WHERE i.id = :id")
    , @NamedQuery(name = "Iclickermodel.findByClickername", query = "SELECT i FROM Iclickermodel i WHERE i.clickername = :clickername")
    , @NamedQuery(name = "Iclickermodel.findByMaterialtype", query = "SELECT i FROM Iclickermodel i WHERE i.materialtype = :materialtype")
    , @NamedQuery(name = "Iclickermodel.findByMaterialcourse", query = "SELECT i FROM Iclickermodel i WHERE i.materialcourse = :materialcourse")
    , @NamedQuery(name = "Iclickermodel.findByRequired", query = "SELECT i FROM Iclickermodel i WHERE i.required = :required")
    , @NamedQuery(name = "Iclickermodel.findByClickerregristationnumber", query = "SELECT i FROM Iclickermodel i WHERE i.clickerregristationnumber = :clickerregristationnumber")})
public class Iclickermodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Boolean id;
    @Basic(optional = false)
    @Column(name = "CLICKERNAME")
    private String clickername;
    @Basic(optional = false)
    @Column(name = "MATERIALTYPE")
    private String materialtype;
    @Basic(optional = false)
    @Column(name = "MATERIALCOURSE")
    private String materialcourse;
    @Basic(optional = false)
    @Column(name = "REQUIRED")
    private Boolean required;
    @Basic(optional = false)
    @Column(name = "CLICKERREGRISTATIONNUMBER")
    private int clickerregristationnumber;

    public Iclickermodel() {
    }

    public Iclickermodel(Boolean id) {
        this.id = id;
    }

    public Iclickermodel(Boolean id, String clickername, String materialtype, String materialcourse, Boolean required, int clickerregristationnumber) {
        this.id = id;
        this.clickername = clickername;
        this.materialtype = materialtype;
        this.materialcourse = materialcourse;
        this.required = required;
        this.clickerregristationnumber = clickerregristationnumber;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    public String getClickername() {
        return clickername;
    }

    public void setClickername(String clickername) {
        this.clickername = clickername;
    }

    public String getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype;
    }

    public String getMaterialcourse() {
        return materialcourse;
    }

    public void setMaterialcourse(String materialcourse) {
        this.materialcourse = materialcourse;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public int getClickerregristationnumber() {
        return clickerregristationnumber;
    }

    public void setClickerregristationnumber(int clickerregristationnumber) {
        this.clickerregristationnumber = clickerregristationnumber;
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
        if (!(object instanceof Iclickermodel)) {
            return false;
        }
        Iclickermodel other = (Iclickermodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Iclickermodel[ id=" + id + " ]";
    }
    
}
