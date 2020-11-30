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
@Table(name = "ISBNFILTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Isbnfilter.findAll", query = "SELECT i FROM Isbnfilter i")
    , @NamedQuery(name = "Isbnfilter.findByFilterid", query = "SELECT i FROM Isbnfilter i WHERE i.filterid = :filterid")
    , @NamedQuery(name = "Isbnfilter.findByFiltertype", query = "SELECT i FROM Isbnfilter i WHERE i.filtertype = :filtertype")})
public class Isbnfilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FILTERID")
    private Integer filterid;
    @Basic(optional = false)
    @Column(name = "FILTERTYPE")
    private String filtertype;

    public Isbnfilter() {
    }

    public Isbnfilter(Integer filterid) {
        this.filterid = filterid;
    }

    public Isbnfilter(Integer filterid, String filtertype) {
        this.filterid = filterid;
        this.filtertype = filtertype;
    }

    public Integer getFilterid() {
        return filterid;
    }

    public void setFilterid(Integer filterid) {
        this.filterid = filterid;
    }

    public String getFiltertype() {
        return filtertype;
    }

    public void setFiltertype(String filtertype) {
        this.filtertype = filtertype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filterid != null ? filterid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Isbnfilter)) {
            return false;
        }
        Isbnfilter other = (Isbnfilter) object;
        if ((this.filterid == null && other.filterid != null) || (this.filterid != null && !this.filterid.equals(other.filterid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Isbnfilter[ filterid=" + filterid + " ]";
    }
    
}
