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
@Table(name = "POSTMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postmodel.findAll", query = "SELECT p FROM Postmodel p")
    , @NamedQuery(name = "Postmodel.findByPostid", query = "SELECT p FROM Postmodel p WHERE p.postid = :postid")
    , @NamedQuery(name = "Postmodel.findByPostname", query = "SELECT p FROM Postmodel p WHERE p.postname = :postname")
    , @NamedQuery(name = "Postmodel.findByCoursematerial", query = "SELECT p FROM Postmodel p WHERE p.coursematerial = :coursematerial")})
public class Postmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "POSTID")
    private Integer postid;
    @Basic(optional = false)
    @Column(name = "POSTNAME")
    private String postname;
    @Basic(optional = false)
    @Column(name = "COURSEMATERIAL")
    private String coursematerial;

    public Postmodel() {
    }

    public Postmodel(Integer postid) {
        this.postid = postid;
    }

    public Postmodel(Integer postid, String postname, String coursematerial) {
        this.postid = postid;
        this.postname = postname;
        this.coursematerial = coursematerial;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getCoursematerial() {
        return coursematerial;
    }

    public void setCoursematerial(String coursematerial) {
        this.coursematerial = coursematerial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postid != null ? postid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postmodel)) {
            return false;
        }
        Postmodel other = (Postmodel) object;
        if ((this.postid == null && other.postid != null) || (this.postid != null && !this.postid.equals(other.postid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Postmodel[ postid=" + postid + " ]";
    }
    
}
