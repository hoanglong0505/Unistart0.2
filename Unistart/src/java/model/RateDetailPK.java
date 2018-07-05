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

/**
 *
 * @author Admin
 */
@Embeddable
public class RateDetailPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "RateId", nullable = false)
    private int rateId;
    @Basic(optional = false)
    @Column(name = "CriteriaId", nullable = false)
    private int criteriaId;

    public RateDetailPK() {
    }

    public RateDetailPK(int rateId, int criteriaId) {
        this.rateId = rateId;
        this.criteriaId = criteriaId;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
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
        hash += (int) rateId;
        hash += (int) criteriaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RateDetailPK)) {
            return false;
        }
        RateDetailPK other = (RateDetailPK) object;
        if (this.rateId != other.rateId) {
            return false;
        }
        if (this.criteriaId != other.criteriaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.RateDetailPK[ rateId=" + rateId + ", criteriaId=" + criteriaId + " ]";
    }
    
}
