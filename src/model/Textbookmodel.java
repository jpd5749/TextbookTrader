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
 * @author Jonas
 */
@Entity
@Table(name = "TEXTBOOKMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Textbookmodel.findAll", query = "SELECT t FROM Textbookmodel t")
    , @NamedQuery(name = "Textbookmodel.findByIsbnnumber", query = "SELECT t FROM Textbookmodel t WHERE t.isbnnumber = :isbnnumber")
    , @NamedQuery(name = "Textbookmodel.findByTextbookname", query = "SELECT t FROM Textbookmodel t WHERE t.textbookname = :textbookname")
    , @NamedQuery(name = "Textbookmodel.findByConditionofbook", query = "SELECT t FROM Textbookmodel t WHERE t.conditionofbook = :conditionofbook")
    , @NamedQuery(name = "Textbookmodel.findByMaterialtype", query = "SELECT t FROM Textbookmodel t WHERE t.materialtype = :materialtype")
    , @NamedQuery(name = "Textbookmodel.findByMaterialcourse", query = "SELECT t FROM Textbookmodel t WHERE t.materialcourse = :materialcourse")})
public class Textbookmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ISBNNUMBER")
    private Integer isbnnumber;
    @Column(name = "TEXTBOOKNAME")
    private String textbookname;
    @Column(name = "CONDITIONOFBOOK")
    private String conditionofbook;
    @Column(name = "MATERIALTYPE")
    private String materialtype;
    @Column(name = "MATERIALCOURSE")
    private String materialcourse;

    public Textbookmodel() {
    }

    public Textbookmodel(Integer isbnnumber) {
        this.isbnnumber = isbnnumber;
    }

    public Integer getIsbnnumber() {
        return isbnnumber;
    }

    public void setIsbnnumber(Integer isbnnumber) {
        this.isbnnumber = isbnnumber;
    }

    public String getTextbookname() {
        return textbookname;
    }

    public void setTextbookname(String textbookname) {
        this.textbookname = textbookname;
    }

    public String getConditionofbook() {
        return conditionofbook;
    }

    public void setConditionofbook(String conditionofbook) {
        this.conditionofbook = conditionofbook;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbnnumber != null ? isbnnumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Textbookmodel)) {
            return false;
        }
        Textbookmodel other = (Textbookmodel) object;
        if ((this.isbnnumber == null && other.isbnnumber != null) || (this.isbnnumber != null && !this.isbnnumber.equals(other.isbnnumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Textbookmodel[ isbnnumber=" + isbnnumber + " ]";
    }
    
}
