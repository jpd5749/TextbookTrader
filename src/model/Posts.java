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
@Table(name = "POSTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p")
    , @NamedQuery(name = "Posts.findById", query = "SELECT p FROM Posts p WHERE p.id = :id")
    , @NamedQuery(name = "Posts.findByTitle", query = "SELECT p FROM Posts p WHERE p.title = :title")
    , @NamedQuery(name = "Posts.findByTitleAdvanced", query = "SELECT p FROM Posts p WHERE  LOWER(p.title) LIKE  CONCAT('%', LOWER(:title), '%')")
    , @NamedQuery(name = "Posts.findByCondition", query = "SELECT p FROM Posts p WHERE p.condition = :condition")
    , @NamedQuery(name = "Posts.findByType", query = "SELECT p FROM Posts p WHERE p.type = :type")
    , @NamedQuery(name = "Posts.findByCourse", query = "SELECT p FROM Posts p WHERE p.course = :course")
    , @NamedQuery(name = "Posts.findByUserid", query = "SELECT p FROM Posts p WHERE p.userid = :userid")
})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @Column(name = "CONDITION")
    private String condition;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "COURSE")
    private String course;
    @Basic(optional = false)
    @Column(name = "USERID")
    private int userid;

    public Posts() {
    }

    public Posts(Integer id) {
        this.id = id;
    }

    public Posts(Integer id, String title, String condition, String type, int userid) {
        this.id = id;
        this.title = title;
        this.condition = condition;
        this.type = type;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Posts[ id=" + id + " ]";
    }
    
}
