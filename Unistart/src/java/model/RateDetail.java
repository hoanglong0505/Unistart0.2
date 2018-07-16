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
@Table(name = "RateDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RateDetail.findAll", query = "SELECT r FROM RateDetail r")
    , @NamedQuery(name = "RateDetail.findByRateId", query = "SELECT r FROM RateDetail r WHERE r.rateDetailPK.rateId = :rateId")
    , @NamedQuery(name = "RateDetail.findByCriteriaId", query = "SELECT r FROM RateDetail r WHERE r.rateDetailPK.criteriaId = :criteriaId")
    , @NamedQuery(name = "RateDetail.findByValue", query = "SELECT r FROM RateDetail r WHERE r.value = :value")})
public class RateDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RateDetailPK rateDetailPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Value")
    private Double value;
    @JoinColumn(name = "RateId", referencedColumnName = "RateId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rate rate;
    @JoinColumn(name = "CriteriaId", referencedColumnName = "CriteriaId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RateCriteria rateCriteria;

    public RateDetail() {
    }

    public RateDetail(RateDetailPK rateDetailPK) {
        this.rateDetailPK = rateDetailPK;
    }

    public RateDetail(int rateId, int criteriaId) {
        this.rateDetailPK = new RateDetailPK(rateId, criteriaId);
    }

    public RateDetailPK getRateDetailPK() {
        return rateDetailPK;
    }

    public void setRateDetailPK(RateDetailPK rateDetailPK) {
        this.rateDetailPK = rateDetailPK;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Rate getRate() {
        return null;
//        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public RateCriteria getRateCriteria() {
        return null;
//        return rateCriteria;
    }

    public void setRateCriteria(RateCriteria rateCriteria) {
        this.rateCriteria = rateCriteria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rateDetailPK != null ? rateDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RateDetail)) {
            return false;
        }
        RateDetail other = (RateDetail) object;
        if ((this.rateDetailPK == null && other.rateDetailPK != null) || (this.rateDetailPK != null && !this.rateDetailPK.equals(other.rateDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.RateDetail[ rateDetailPK=" + rateDetailPK + " ]";
    }
    
}
