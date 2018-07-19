/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TNT
 */
@Entity
@Table(name = "AverageRate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AverageRate.findAll", query = "SELECT a FROM AverageRate a")
    , @NamedQuery(name = "AverageRate.findBySchoolId", query = "SELECT a FROM AverageRate a WHERE a.averageRatePK.schoolId = :schoolId")
    , @NamedQuery(name = "AverageRate.findByCriteriaId", query = "SELECT a FROM AverageRate a WHERE a.averageRatePK.criteriaId = :criteriaId")
    , @NamedQuery(name = "AverageRate.findByAvgValue", query = "SELECT a FROM AverageRate a WHERE a.avgValue = :avgValue")})
public class AverageRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AverageRatePK averageRatePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AvgValue")
    private Double avgValue;
    @JoinColumn(name = "CriteriaId", referencedColumnName = "CriteriaId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RateCriteria rateCriteria;
    @JoinColumn(name = "SchoolId", referencedColumnName = "SchoolId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private School school;

    public AverageRate() {
    }

    public AverageRate(AverageRatePK averageRatePK) {
        this.averageRatePK = averageRatePK;
    }

    public AverageRate(int schoolId, int criteriaId) {
        this.averageRatePK = new AverageRatePK(schoolId, criteriaId);
    }

    public AverageRatePK getAverageRatePK() {
        return averageRatePK;
    }

    public void setAverageRatePK(AverageRatePK averageRatePK) {
        this.averageRatePK = averageRatePK;
    }

    public Double getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(Double avgValue) {
        this.avgValue = avgValue;
    }

    public RateCriteria getRateCriteria() {
        return rateCriteria;
    }

    public void setRateCriteria(RateCriteria rateCriteria) {
        this.rateCriteria = rateCriteria;
    }

    public School getSchool() {
        return null;
//        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (averageRatePK != null ? averageRatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AverageRate)) {
            return false;
        }
        AverageRate other = (AverageRate) object;
        if ((this.averageRatePK == null && other.averageRatePK != null) || (this.averageRatePK != null && !this.averageRatePK.equals(other.averageRatePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AverageRate[ averageRatePK=" + averageRatePK + " ]";
    }
    
}
