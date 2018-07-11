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
@Table(name = "RateCriteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RateCriteria.findAll", query = "SELECT r FROM RateCriteria r")
    , @NamedQuery(name = "RateCriteria.findByCriteriaId", query = "SELECT r FROM RateCriteria r WHERE r.criteriaId = :criteriaId")
    , @NamedQuery(name = "RateCriteria.findByCriteriaName", query = "SELECT r FROM RateCriteria r WHERE r.criteriaName = :criteriaName")
    , @NamedQuery(name = "RateCriteria.findByStatus", query = "SELECT r FROM RateCriteria r WHERE r.status = :status")})
public class RateCriteria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CriteriaId")
    private Integer criteriaId;
    @Size(max = 255)
    @Column(name = "CriteriaName")
    private String criteriaName;
    @Column(name = "Status")
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rateCriteria")
    private Collection<RateDetail> rateDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rateCriteria")
    private Collection<AverageRate> averageRateCollection;

    public RateCriteria() {
    }

    public RateCriteria(Integer criteriaId) {
        this.criteriaId = criteriaId;
    }

    public Integer getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Integer criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<RateDetail> getRateDetailCollection() {
        return rateDetailCollection;
    }

    public void setRateDetailCollection(Collection<RateDetail> rateDetailCollection) {
        this.rateDetailCollection = rateDetailCollection;
    }

    @XmlTransient
    public Collection<AverageRate> getAverageRateCollection() {
        return averageRateCollection;
    }

    public void setAverageRateCollection(Collection<AverageRate> averageRateCollection) {
        this.averageRateCollection = averageRateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (criteriaId != null ? criteriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RateCriteria)) {
            return false;
        }
        RateCriteria other = (RateCriteria) object;
        if ((this.criteriaId == null && other.criteriaId != null) || (this.criteriaId != null && !this.criteriaId.equals(other.criteriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.RateCriteria[ criteriaId=" + criteriaId + " ]";
    }
    
}
