/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Genitite", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genitite.findAll", query = "SELECT g FROM Genitite g")
    , @NamedQuery(name = "Genitite.findByGenitiveId", query = "SELECT g FROM Genitite g WHERE g.genitiveId = :genitiveId")
    , @NamedQuery(name = "Genitite.findByGentiveCode", query = "SELECT g FROM Genitite g WHERE g.gentiveCode = :gentiveCode")})
public class Genitite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GenitiveId", nullable = false)
    private Integer genitiveId;
    @Basic(optional = false)
    @Column(name = "GentiveCode", nullable = false, length = 5)
    private String gentiveCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genititeId")
    private List<Answer> answerList;

    public Genitite() {
    }

    public Genitite(Integer genitiveId) {
        this.genitiveId = genitiveId;
    }

    public Genitite(Integer genitiveId, String gentiveCode) {
        this.genitiveId = genitiveId;
        this.gentiveCode = gentiveCode;
    }

    public Integer getGenitiveId() {
        return genitiveId;
    }

    public void setGenitiveId(Integer genitiveId) {
        this.genitiveId = genitiveId;
    }

    public String getGentiveCode() {
        return gentiveCode;
    }

    public void setGentiveCode(String gentiveCode) {
        this.gentiveCode = gentiveCode;
    }

    @XmlTransient
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genitiveId != null ? genitiveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genitite)) {
            return false;
        }
        Genitite other = (Genitite) object;
        if ((this.genitiveId == null && other.genitiveId != null) || (this.genitiveId != null && !this.genitiveId.equals(other.genitiveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Genitite[ genitiveId=" + genitiveId + " ]";
    }
    
}
