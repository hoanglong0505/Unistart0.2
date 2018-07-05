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
public class ReportPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "RateId", nullable = false)
    private int rateId;
    @Basic(optional = false)
    @Column(name = "UserId", nullable = false)
    private int userId;

    public ReportPK() {
    }

    public ReportPK(int rateId, int userId) {
        this.rateId = rateId;
        this.userId = userId;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) rateId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportPK)) {
            return false;
        }
        ReportPK other = (ReportPK) object;
        if (this.rateId != other.rateId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ReportPK[ rateId=" + rateId + ", userId=" + userId + " ]";
    }
    
}
