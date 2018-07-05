/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "SubjectCombination", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectCombination.findAll", query = "SELECT s FROM SubjectCombination s")
    , @NamedQuery(name = "SubjectCombination.findBySjCombiCode", query = "SELECT s FROM SubjectCombination s WHERE s.sjCombiCode = :sjCombiCode")
    , @NamedQuery(name = "SubjectCombination.findBySjCombiName", query = "SELECT s FROM SubjectCombination s WHERE s.sjCombiName = :sjCombiName")})
public class SubjectCombination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SjCombiCode", nullable = false, length = 5)
    private String sjCombiCode;
    @Basic(optional = false)
    @Column(name = "SjCombiName", nullable = false, length = 500)
    private String sjCombiName;
    @ManyToMany(mappedBy = "subjectCombinationList")
    private List<EntranceInfo> entranceInfoList;

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
    public List<EntranceInfo> getEntranceInfoList() {
        return entranceInfoList;
    }

    public void setEntranceInfoList(List<EntranceInfo> entranceInfoList) {
        this.entranceInfoList = entranceInfoList;
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
