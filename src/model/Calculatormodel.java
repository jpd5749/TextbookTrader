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
@Table(name = "CALCULATORMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calculatormodel.findAll", query = "SELECT c FROM Calculatormodel c")
    , @NamedQuery(name = "Calculatormodel.findById", query = "SELECT c FROM Calculatormodel c WHERE c.id = :id")
    , @NamedQuery(name = "Calculatormodel.findByMaterialtype", query = "SELECT c FROM Calculatormodel c WHERE c.materialtype = :materialtype")
    , @NamedQuery(name = "Calculatormodel.findByMaterialcourse", query = "SELECT c FROM Calculatormodel c WHERE c.materialcourse = :materialcourse")
    , @NamedQuery(name = "Calculatormodel.findByRequired", query = "SELECT c FROM Calculatormodel c WHERE c.required = :required")
    , @NamedQuery(name = "Calculatormodel.findByBatteriesrequired", query = "SELECT c FROM Calculatormodel c WHERE c.batteriesrequired = :batteriesrequired")})
public class Calculatormodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
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
    @Column(name = "BATTERIESREQUIRED")
    private Boolean batteriesrequired;

    public Calculatormodel() {
    }

    public Calculatormodel(Integer id) {
        this.id = id;
    }

    public Calculatormodel(Integer id, String materialtype, String materialcourse, Boolean required, Boolean batteriesrequired) {
        this.id = id;
        this.materialtype = materialtype;
        this.materialcourse = materialcourse;
        this.required = required;
        this.batteriesrequired = batteriesrequired;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getBatteriesrequired() {
        return batteriesrequired;
    }

    public void setBatteriesrequired(Boolean batteriesrequired) {
        this.batteriesrequired = batteriesrequired;
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
        if (!(object instanceof Calculatormodel)) {
            return false;
        }
        Calculatormodel other = (Calculatormodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Calculatormodel[ id=" + id + " ]";
    }
    
}
