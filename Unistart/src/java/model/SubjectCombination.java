/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "SubjectCombination")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectCombination.findAll", query = "SELECT s FROM SubjectCombination s")
    , @NamedQuery(name = "SubjectCombination.findBySjCombiCode", query = "SELECT s FROM SubjectCombination s WHERE s.sjCombiCode = :sjCombiCode")
    , @NamedQuery(name = "SubjectCombination.findBySjCombiName", query = "SELECT s FROM SubjectCombination s WHERE s.sjCombiName = :sjCombiName")})
public class SubjectCombination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SjCombiCode")
    private String sjCombiCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "SjCombiName")
    private String sjCombiName;
    @ManyToMany(mappedBy = "subjectCombinations")
    private Collection<EntranceInfo> entranceInfos;

    public SubjectCombination() {
    }

    public SubjectCombination(String sjCombiCode) {
        this.sjCombiCode = sjCombiCode;
    }

    public SubjectCombination(String sjCombiCode, String sjCombiName) {
        this.sjCombiCode = sjCombiCode;
        this.sjCombiName = sjCombiName;
    }

    public String getSjCombiCode() {
        return sjCombiCode;
    }

    public void setSjCombiCode(String sjCombiCode) {
        this.sjCombiCode = sjCombiCode;
    }

    public String getSjCombiName() {
        return sjCombiName;
    }

    public void setSjCombiName(String sjCombiName) {
        this.sjCombiName = sjCombiName;
    }

    @XmlTransient
    public Collection<EntranceInfo> getEntranceInfos() {
        return entranceInfos;
    }

    public void setEntranceInfos(Collection<EntranceInfo> entranceInfos) {
        this.entranceInfos = entranceInfos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sjCombiCode != null ? sjCombiCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectCombination)) {
            return false;
        }
        SubjectCombination other = (SubjectCombination) object;
        if ((this.sjCombiCode == null && other.sjCombiCode != null) || (this.sjCombiCode != null && !this.sjCombiCode.equals(other.sjCombiCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SubjectCombination[ sjCombiCode=" + sjCombiCode + " ]";
    }
    
}
