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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author TNT
 */
@Entity
@Table(name = "EntranceInfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntranceInfo.findAll", query = "SELECT e FROM EntranceInfo e")
    , @NamedQuery(name = "EntranceInfo.findByEntranceId", query = "SELECT e FROM EntranceInfo e WHERE e.entranceId = :entranceId")
    , @NamedQuery(name = "EntranceInfo.findByYear", query = "SELECT e FROM EntranceInfo e WHERE e.year = :year")
    , @NamedQuery(name = "EntranceInfo.findBySubName", query = "SELECT e FROM EntranceInfo e WHERE e.subName = :subName")
    , @NamedQuery(name = "EntranceInfo.findBySubCode", query = "SELECT e FROM EntranceInfo e WHERE e.subCode = :subCode")
    , @NamedQuery(name = "EntranceInfo.findByNormalEntranceAmount", query = "SELECT e FROM EntranceInfo e WHERE e.normalEntranceAmount = :normalEntranceAmount")
    , @NamedQuery(name = "EntranceInfo.findByOtherEntranceAmount", query = "SELECT e FROM EntranceInfo e WHERE e.otherEntranceAmount = :otherEntranceAmount")
    , @NamedQuery(name = "EntranceInfo.findByMinPoint", query = "SELECT e FROM EntranceInfo e WHERE e.minPoint = :minPoint")
    , @NamedQuery(name = "EntranceInfo.findByNote", query = "SELECT e FROM EntranceInfo e WHERE e.note = :note")})
public class EntranceInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EntranceId")
    private Integer entranceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Year")
    private int year;
    @Size(max = 500)
    @Column(name = "SubName")
    private String subName;
    @Size(max = 50)
    @Column(name = "SubCode")
    private String subCode;
    @Column(name = "NormalEntranceAmount")
    private Integer normalEntranceAmount;
    @Column(name = "OtherEntranceAmount")
    private Integer otherEntranceAmount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MinPoint")
    private Double minPoint;
    @Size(max = 2147483647)
    @Column(name = "Note")
    private String note;
    @JoinTable(name = "EntranceSubject", joinColumns = {
        @JoinColumn(name = "EntranceId", referencedColumnName = "EntranceId")}, inverseJoinColumns = {
        @JoinColumn(name = "SjCombiCode", referencedColumnName = "SjCombiCode")})
    @ManyToMany
    private Collection<SubjectCombination> subjectCombinations;
    @JoinColumn(name = "FieldId", referencedColumnName = "FieldId")
    @ManyToOne(optional = false)
    private Field field;
    @JoinColumn(name = "SchoolId", referencedColumnName = "SchoolId")
    @ManyToOne(optional = false)
    private School school;

    public EntranceInfo() {
    }

    public EntranceInfo(Integer entranceId) {
        this.entranceId = entranceId;
    }

    public EntranceInfo(Integer entranceId, int year) {
        this.entranceId = entranceId;
        this.year = year;
    }

    public Integer getEntranceId() {
        return entranceId;
    }

    public void setEntranceId(Integer entranceId) {
        this.entranceId = entranceId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public Integer getNormalEntranceAmount() {
        return normalEntranceAmount;
    }

    public void setNormalEntranceAmount(Integer normalEntranceAmount) {
        this.normalEntranceAmount = normalEntranceAmount;
    }

    public Integer getOtherEntranceAmount() {
        return otherEntranceAmount;
    }

    public void setOtherEntranceAmount(Integer otherEntranceAmount) {
        this.otherEntranceAmount = otherEntranceAmount;
    }

    public Double getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(Double minPoint) {
        this.minPoint = minPoint;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SubjectCombination> getSubjectCombinations() {
        return subjectCombinations;
    }

    public void setSubjectCombinations(Collection<SubjectCombination> subjectCombinations) {
        this.subjectCombinations = subjectCombinations;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entranceId != null ? entranceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntranceInfo)) {
            return false;
        }
        EntranceInfo other = (EntranceInfo) object;
        if ((this.entranceId == null && other.entranceId != null) || (this.entranceId != null && !this.entranceId.equals(other.entranceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.EntranceInfo[ entranceId=" + entranceId + " ]";
    }
    
}
