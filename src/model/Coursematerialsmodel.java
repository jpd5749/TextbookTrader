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
@Table(name = "COURSEMATERIALSMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coursematerialsmodel.findAll", query = "SELECT c FROM Coursematerialsmodel c")
    , @NamedQuery(name = "Coursematerialsmodel.findById", query = "SELECT c FROM Coursematerialsmodel c WHERE c.id = :id")
    , @NamedQuery(name = "Coursematerialsmodel.findByMaterialname", query = "SELECT c FROM Coursematerialsmodel c WHERE c.materialname = :materialname")
    , @NamedQuery(name = "Coursematerialsmodel.findByMaterialtype", query = "SELECT c FROM Coursematerialsmodel c WHERE c.materialtype = :materialtype")
    , @NamedQuery(name = "Coursematerialsmodel.findByMaterialclass", query = "SELECT c FROM Coursematerialsmodel c WHERE c.materialclass = :materialclass")
    , @NamedQuery(name = "Coursematerialsmodel.findByRequired", query = "SELECT c FROM Coursematerialsmodel c WHERE c.required = :required")})
public class Coursematerialsmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "MATERIALNAME")
    private String materialname;
    @Basic(optional = false)
    @Column(name = "MATERIALTYPE")
    private String materialtype;
    @Basic(optional = false)
    @Column(name = "MATERIALCLASS")
    private String materialclass;
    @Basic(optional = false)
    @Column(name = "REQUIRED")
    private Boolean required;

    public Coursematerialsmodel() {
    }

    public Coursematerialsmodel(Integer id) {
        this.id = id;
    }

    public Coursematerialsmodel(Integer id, String materialname, String materialtype, String materialclass, Boolean required) {
        this.id = id;
        this.materialname = materialname;
        this.materialtype = materialtype;
        this.materialclass = materialclass;
        this.required = required;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype;
    }

    public String getMaterialclass() {
        return materialclass;
    }

    public void setMaterialclass(String materialclass) {
        this.materialclass = materialclass;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
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
        if (!(object instanceof Coursematerialsmodel)) {
            return false;
        }
        Coursematerialsmodel other = (Coursematerialsmodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Coursematerialsmodel[ id=" + id + " ]";
    }
    
}
