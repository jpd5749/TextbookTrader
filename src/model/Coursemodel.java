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
@Table(name = "COURSEMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coursemodel.findAll", query = "SELECT c FROM Coursemodel c")
    , @NamedQuery(name = "Coursemodel.findByCourseid", query = "SELECT c FROM Coursemodel c WHERE c.courseid = :courseid")
    , @NamedQuery(name = "Coursemodel.findByCoursename", query = "SELECT c FROM Coursemodel c WHERE c.coursename = :coursename")
    , @NamedQuery(name = "Coursemodel.findByCoursesection", query = "SELECT c FROM Coursemodel c WHERE c.coursesection = :coursesection")
    , @NamedQuery(name = "Coursemodel.findByCredits", query = "SELECT c FROM Coursemodel c WHERE c.credits = :credits")})
public class Coursemodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COURSEID")
    private Integer courseid;
    @Basic(optional = false)
    @Column(name = "COURSENAME")
    private String coursename;
    @Basic(optional = false)
    @Column(name = "COURSESECTION")
    private int coursesection;
    @Basic(optional = false)
    @Column(name = "CREDITS")
    private int credits;

    public Coursemodel() {
    }

    public Coursemodel(Integer courseid) {
        this.courseid = courseid;
    }

    public Coursemodel(Integer courseid, String coursename, int coursesection, int credits) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.coursesection = coursesection;
        this.credits = credits;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getCoursesection() {
        return coursesection;
    }

    public void setCoursesection(int coursesection) {
        this.coursesection = coursesection;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseid != null ? courseid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coursemodel)) {
            return false;
        }
        Coursemodel other = (Coursemodel) object;
        if ((this.courseid == null && other.courseid != null) || (this.courseid != null && !this.courseid.equals(other.courseid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Coursemodel[ courseid=" + courseid + " ]";
    }
    
}
