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
@Table(name = "USERMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usermodel.findAll", query = "SELECT u FROM Usermodel u")
    , @NamedQuery(name = "Usermodel.findById", query = "SELECT u FROM Usermodel u WHERE u.id = :id")
    , @NamedQuery(name = "Usermodel.findByUsername", query = "SELECT u FROM Usermodel u WHERE u.username = :username")
    , @NamedQuery(name = "Usermodel.findByPassword", query = "SELECT u FROM Usermodel u WHERE u.password = :password")
    , @NamedQuery(name = "Usermodel.findByEmailaddress", query = "SELECT u FROM Usermodel u WHERE u.emailaddress = :emailaddress")})
public class Usermodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "EMAILADDRESS")
    private String emailaddress;

    public Usermodel() {
    }

    public Usermodel(Integer id) {
        this.id = id;
    }

    public Usermodel(Integer id, String username, String password, String emailaddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailaddress = emailaddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
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
        if (!(object instanceof Usermodel)) {
            return false;
        }
        Usermodel other = (Usermodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usermodel[ id=" + id + " ]";
    }
    
}
