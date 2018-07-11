/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TNT
 */
@Entity
@Table(name = "Report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByRateId", query = "SELECT r FROM Report r WHERE r.reportPK.rateId = :rateId")
    , @NamedQuery(name = "Report.findByUserId", query = "SELECT r FROM Report r WHERE r.reportPK.userId = :userId")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReportPK reportPK;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "RpContent")
    private String rpContent;
    @JoinColumn(name = "RateId", referencedColumnName = "RateId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rate rate;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Report() {
    }

    public Report(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public Report(ReportPK reportPK, String rpContent) {
        this.reportPK = reportPK;
        this.rpContent = rpContent;
    }

    public Report(int rateId, String userId) {
        this.reportPK = new ReportPK(rateId, userId);
    }

    public ReportPK getReportPK() {
        return reportPK;
    }

    public void setReportPK(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public String getRpContent() {
        return rpContent;
    }

    public void setRpContent(String rpContent) {
        this.rpContent = rpContent;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportPK != null ? reportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportPK == null && other.reportPK != null) || (this.reportPK != null && !this.reportPK.equals(other.reportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Report[ reportPK=" + reportPK + " ]";
    }
    
}
