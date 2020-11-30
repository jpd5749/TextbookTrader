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
@Table(name = "UNIVERSITYMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Universitymodel.findAll", query = "SELECT u FROM Universitymodel u")
    , @NamedQuery(name = "Universitymodel.findByUniversityid", query = "SELECT u FROM Universitymodel u WHERE u.universityid = :universityid")
    , @NamedQuery(name = "Universitymodel.findByUniversityname", query = "SELECT u FROM Universitymodel u WHERE u.universityname = :universityname")})
public class Universitymodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UNIVERSITYID")
    private Integer universityid;
    @Basic(optional = false)
    @Column(name = "UNIVERSITYNAME")
    private String universityname;

    public Universitymodel() {
    }

    public Universitymodel(Integer universityid) {
        this.universityid = universityid;
    }

    public Universitymodel(Integer universityid, String universityname) {
        this.universityid = universityid;
        this.universityname = universityname;
    }

    public Integer getUniversityid() {
        return universityid;
    }

    public void setUniversityid(Integer universityid) {
        this.universityid = universityid;
    }

    public String getUniversityname() {
        return universityname;
    }

    public void setUniversityname(String universityname) {
        this.universityname = universityname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (universityid != null ? universityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universitymodel)) {
            return false;
        }
        Universitymodel other = (Universitymodel) object;
        if ((this.universityid == null && other.universityid != null) || (this.universityid != null && !this.universityid.equals(other.universityid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Universitymodel[ universityid=" + universityid + " ]";
    }
    
}
