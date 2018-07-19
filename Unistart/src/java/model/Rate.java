/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import app.Constants;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
    , @NamedQuery(name = "Rate.findByTitle", query = "SELECT r FROM Rate r WHERE r.title = :title")
    , @NamedQuery(name = "Rate.findByPros", query = "SELECT r FROM Rate r WHERE r.pros = :pros")
    , @NamedQuery(name = "Rate.findByCons", query = "SELECT r FROM Rate r WHERE r.cons = :cons")
    , @NamedQuery(name = "Rate.findByExperience", query = "SELECT r FROM Rate r WHERE r.experience = :experience")
    , @NamedQuery(name = "Rate.findByEncourage", query = "SELECT r FROM Rate r WHERE r.encourage = :encourage")
    , @NamedQuery(name = "Rate.findByAnonymous", query = "SELECT r FROM Rate r WHERE r.anonymous = :anonymous")})
public class Rate implements Serializable {

    @Column(name = "SubmitTime")
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date submitTime;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RateId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Title")
    private String title;
    @Size(max = 500)
    @Column(name = "Pros")
    private String pros;
    @Size(max = 500)
    @Column(name = "Cons")
    private String cons;
    @Size(max = 500)
    @Column(name = "Experience")
    private String experience;
    @Column(name = "Encourage")
    private Boolean encourage;
    @Column(name = "Anonymous")
    private Boolean anonymous;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rate")
    private Collection<RateDetail> rateDetails;

    @XmlTransient
    @Column(name = "SubmitDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date submitDate;

    @Transient
    private Double averageValue;

    public Double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Double averageValue) {
        this.averageValue = averageValue;
    }

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

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    @Transient
    private String submitDateStr;

    public void setSubmitDateStr(String submitDateStr) {
        this.submitDateStr = submitDateStr;
    }

    public String getSubmitDateStr() {
        if (submitDate != null) {
            submitDateStr = new SimpleDateFormat("dd/MM/yyyy").format(submitDate);
            return submitDateStr;
        }
        return null;
    }

    @XmlTransient
    public Collection<RateDetail> getRateDetails() {
        return rateDetails;
    }

    public void setRateDetails(Collection<RateDetail> rateDetails) {
        this.rateDetails = rateDetails;
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

    //=============== RELATIONSHIP HANDLER ===============
    //SCHOOL
    @XmlTransient
    @Transient
    public int schoolHandler = Constants.GENERATE;

    @JoinColumn(name = "SchoolId", referencedColumnName = "SchoolId")
    @ManyToOne
    private School school;

    public School getSchool() {
        if (schoolHandler == Constants.GENERATE) {
            setSchoolBiDir(Constants.TRANSIENT);
            return school;
        }
        return null;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setSchoolBiDir(int MODE) {
        school.ratesHandler = MODE;
    }

    //----------------------------------
    //user
    @XmlTransient
    @Transient
    public int userHandler = Constants.GENERATE;
    
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private Users user;

    public Users getUser() {
        if (userHandler == Constants.GENERATE) {
            setUserBiDir(Constants.TRANSIENT);
            return user;
        }
        return null;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public void setUserBiDir(int MODE) {
        user.ratesHandler = MODE;
    }
    
    //----------------------------------
    
    //reports
    @XmlTransient
    @Transient
    public int reportsHandler = Constants.GENERATE;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rate")
    private Collection<Report> reports;
    
    public Collection<Report> getReports() {
        if (reportsHandler == Constants.GENERATE) {
            return reports;
        }
        return null;
    }

    public void setReports(Collection<Report> reports) {
        this.reports = reports;
    }
    
    public void setReportsBiDir(int MODE) {
//        for (Report rp : reports)
//            rp.rateHandler = MODE;
    }
    //----------------------------------
    
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}
