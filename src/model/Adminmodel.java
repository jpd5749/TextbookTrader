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
@Table(name = "ADMINMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adminmodel.findAll", query = "SELECT a FROM Adminmodel a")
    , @NamedQuery(name = "Adminmodel.findByAdminid", query = "SELECT a FROM Adminmodel a WHERE a.adminid = :adminid")
    , @NamedQuery(name = "Adminmodel.findByAdminname", query = "SELECT a FROM Adminmodel a WHERE a.adminname = :adminname")})
public class Adminmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ADMINID")
    private Integer adminid;
    @Basic(optional = false)
    @Column(name = "ADMINNAME")
    private String adminname;

    public Adminmodel() {
    }

    public Adminmodel(Integer adminid) {
        this.adminid = adminid;
    }

    public Adminmodel(Integer adminid, String adminname) {
        this.adminid = adminid;
        this.adminname = adminname;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminid != null ? adminid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adminmodel)) {
            return false;
        }
        Adminmodel other = (Adminmodel) object;
        if ((this.adminid == null && other.adminid != null) || (this.adminid != null && !this.adminid.equals(other.adminid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Adminmodel[ adminid=" + adminid + " ]";
    }
    
}
