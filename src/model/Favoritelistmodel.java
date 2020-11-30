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
@Table(name = "FAVORITELISTMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favoritelistmodel.findAll", query = "SELECT f FROM Favoritelistmodel f")
    , @NamedQuery(name = "Favoritelistmodel.findByListid", query = "SELECT f FROM Favoritelistmodel f WHERE f.listid = :listid")
    , @NamedQuery(name = "Favoritelistmodel.findByListname", query = "SELECT f FROM Favoritelistmodel f WHERE f.listname = :listname")})
public class Favoritelistmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LISTID")
    private Integer listid;
    @Basic(optional = false)
    @Column(name = "LISTNAME")
    private String listname;

    public Favoritelistmodel() {
    }

    public Favoritelistmodel(Integer listid) {
        this.listid = listid;
    }

    public Favoritelistmodel(Integer listid, String listname) {
        this.listid = listid;
        this.listname = listname;
    }

    public Integer getListid() {
        return listid;
    }

    public void setListid(Integer listid) {
        this.listid = listid;
    }

    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listid != null ? listid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favoritelistmodel)) {
            return false;
        }
        Favoritelistmodel other = (Favoritelistmodel) object;
        if ((this.listid == null && other.listid != null) || (this.listid != null && !this.listid.equals(other.listid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Favoritelistmodel[ listid=" + listid + " ]";
    }
    
}
