/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TNT
 */
@Embeddable
public class AverageRatePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SchoolId")
    private int schoolId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CriteriaId")
    private int criteriaId;

    public AverageRatePK() {
    }

    public AverageRatePK(int schoolId, int criteriaId) {
        this.schoolId = schoolId;
        this.criteriaId = criteriaId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(int criteriaId) {
        this.criteriaId = criteriaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) schoolId;
        hash += (int) criteriaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AverageRatePK)) {
            return false;
        }
        AverageRatePK other = (AverageRatePK) object;
        if (this.schoolId != other.schoolId) {
            return false;
        }
        if (this.criteriaId != other.criteriaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AverageRatePK[ schoolId=" + schoolId + ", criteriaId=" + criteriaId + " ]";
    }
    
}
