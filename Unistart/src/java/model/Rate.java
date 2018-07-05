/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Rate", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r")
    , @NamedQuery(name = "Rate.findByRateId", query = "SELECT r FROM Rate r WHERE r.rateId = :rateId")
    , @NamedQuery(name = "Rate.findByTitle", query = "SELECT r FROM Rate r WHERE r.title = :title")
    , @NamedQuery(name = "Rate.findByEncourage", query = "SELECT r FROM Rate r WHERE r.encourage = :encourage")
    , @NamedQuery(name = "Rate.findByUserLike", query = "SELECT r FROM Rate r WHERE r.userLike = :userLike")
    , @NamedQuery(name = "Rate.findByUserDislike", query = "SELECT r FROM Rate r WHERE r.userDislike = :userDislike")
    , @NamedQuery(name = "Rate.findByAnonymous", query = "SELECT r FROM Rate r WHERE r.anonymous = :anonymous")})
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RateId", nullable = false)
    private Integer rateId;
    @Basic(optional = false)
    @Column(name = "Title", nullable = false, length = 255)
    private String title;
    @Lob
    @Column(name = "Pros", length = 2147483647)
    private String pros;
    @Lob
    @Column(name = "Cons", length = 2147483647)
    private String cons;
    @Lob
    @Column(name = "Experience", length = 2147483647)
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
    private List<Report> reportList;
    @JoinColumn(name = "SchoolId", referencedColumnName = "SchoolId")
    @ManyToOne
    private School schoolId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rate")
    private List<RateDetail> rateDetailList;

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
    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public School getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(School schoolId) {
        this.schoolId = schoolId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<RateDetail> getRateDetailList() {
        return rateDetailList;
    }

    public void setRateDetailList(List<RateDetail> rateDetailList) {
        this.rateDetailList = rateDetailList;
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
