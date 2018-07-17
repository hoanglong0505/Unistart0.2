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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Genitive", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genitive.findAll", query = "SELECT g FROM Genitive g")
    , @NamedQuery(name = "Genitive.findByGenitiveId", query = "SELECT g FROM Genitive g WHERE g.genitiveId = :genitiveId")
    , @NamedQuery(name = "Genitive.findByGentiveCode", query = "SELECT g FROM Genitive g WHERE g.gentiveCode = :gentiveCode")
    , @NamedQuery(name = "Genitive.findByGentiveName", query = "SELECT g FROM Genitive g WHERE g.gentiveName = :gentiveName")})
public class Genitive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "GenitiveId", nullable = false)
    private Integer genitiveId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "GentiveCode", nullable = false, length = 5)
    private String gentiveCode;
    @Size(max = 50)
    @Column(name = "GentiveName", length = 50)
    private String gentiveName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genitive")
    private List<Answer> answerList;

    public Genitive() {
    }

    public Genitive(Integer genitiveId) {
        this.genitiveId = genitiveId;
    }

    public Genitive(Integer genitiveId, String gentiveCode) {
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

    public String getGentiveName() {
        return gentiveName;
    }

    public void setGentiveName(String gentiveName) {
        this.gentiveName = gentiveName;
    }

    @XmlTransient
    @JsonIgnore
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
