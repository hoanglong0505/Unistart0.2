/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TNT
 */
@Entity
@Table(name = "Genitive")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genitive.findAll", query = "SELECT g FROM Genitive g")
    , @NamedQuery(name = "Genitive.findByGenitiveId", query = "SELECT g FROM Genitive g WHERE g.genitiveId = :genitiveId")
    , @NamedQuery(name = "Genitive.findByGenitiveCode", query = "SELECT g FROM Genitive g WHERE g.genitiveCode = :genitiveCode")})
public class Genitive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "GenitiveId")
    private Integer genitiveId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "GenitiveCode")
    private String genitiveCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genitiveId")
    private Collection<Answer> answerCollection;

    public Genitive() {
    }

    public Genitive(Integer genitiveId) {
        this.genitiveId = genitiveId;
    }

    public Genitive(Integer genitiveId, String genitiveCode) {
        this.genitiveId = genitiveId;
        this.genitiveCode = genitiveCode;
    }

    public Integer getGenitiveId() {
        return genitiveId;
    }

    public void setGenitiveId(Integer genitiveId) {
        this.genitiveId = genitiveId;
    }

    public String getGenitiveCode() {
        return genitiveCode;
    }

    public void setGenitiveCode(String genitiveCode) {
        this.genitiveCode = genitiveCode;
    }

    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
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
        if (!(object instanceof Genitive)) {
            return false;
        }
        Genitive other = (Genitive) object;
        if ((this.genitiveId == null && other.genitiveId != null) || (this.genitiveId != null && !this.genitiveId.equals(other.genitiveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Genitive[ genitiveId=" + genitiveId + " ]";
    }
    
}
