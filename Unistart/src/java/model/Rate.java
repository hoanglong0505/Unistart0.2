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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "Rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r")
    , @NamedQuery(name = "Rate.findByRateId", query = "SELECT r FROM Rate r WHERE r.rateId = :rateId")
    , @NamedQuery(name = "Rate.findByUserId", query = "SELECT r FROM Rate r WHERE r.userId = :userId")
    , @NamedQuery(name = "Rate.findByTitle", query = "SELECT r FROM Rate r WHERE r.title = :title")
    , @NamedQuery(name = "Rate.findByEncourage", query = "SELECT r FROM Rate r WHERE r.encourage = :encourage")
    , @NamedQuery(name = "Rate.findByUserLike", query = "SELECT r FROM Rate r WHERE r.userLike = :userLike")
    , @NamedQuery(name = "Rate.findByUserDislike", query = "SELECT r FROM Rate r WHERE r.userDislike = :userDislike")
    , @NamedQuery(name = "Rate.findByAnonymous", query = "SELECT r FROM Rate r WHERE r.anonymous = :anonymous")})
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RateId")
    private Integer rateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Title")
    private String title;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Pros")
    private String pros;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Cons")
    private String cons;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Experience")
    private String experience;
    @Column(name = "Encourage")
    private Boolean encourage;
    @Column(name = "UserLike")
    private Integer userLike;
    @Column(name = "UserDislike")
    private Integer userDislike;
    @Column(name = "Anonymous")
    private Boolean anonymous;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rate")
    private Collection<Report> reportCollection;
    @JoinColumn(name = "SchoolId", referencedColumnName = "SchoolId")
    @ManyToOne
    private School schoolId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private Users userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rate")
    private Collection<RateDetail> rateDetailCollection;

    public Rate() {
    }

    public Rate(Integer rateId) {
        this.rateId = rateId;
    }

    public Rate(Integer rateId, String title) {
        this.rateId = rateId;
        this.title = title;
    }

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Boolean getEncourage() {
        return encourage;
    }

    public void setEncourage(Boolean encourage) {
        this.encourage = encourage;
    }

    public Integer getUserLike() {
        return userLike;
    }

    public void setUserLike(Integer userLike) {
        this.userLike = userLike;
    }

    public Integer getUserDislike() {
        return userDislike;
    }

    public void setUserDislike(Integer userDislike) {
        this.userDislike = userDislike;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    public School getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(School schoolId) {
        this.schoolId = schoolId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<RateDetail> getRateDetailCollection() {
        return rateDetailCollection;
    }

    public void setRateDetailCollection(Collection<RateDetail> rateDetailCollection) {
        this.rateDetailCollection = rateDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rateId != null ? rateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.rateId == null && other.rateId != null) || (this.rateId != null && !this.rateId.equals(other.rateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Rate[ rateId=" + rateId + " ]";
    }
    
}
