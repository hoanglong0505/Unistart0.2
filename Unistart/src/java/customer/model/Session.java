/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Session", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Session.findAll", query = "SELECT s FROM Session s")
    , @NamedQuery(name = "Session.findBySessionId", query = "SELECT s FROM Session s WHERE s.sessionId = :sessionId")
    , @NamedQuery(name = "Session.findByDate", query = "SELECT s FROM Session s WHERE s.date = :date")
    , @NamedQuery(name = "Session.findByDateNo", query = "SELECT s FROM Session s WHERE s.dateNo = :dateNo")
    , @NamedQuery(name = "Session.findByStartTime", query = "SELECT s FROM Session s WHERE s.startTime = :startTime")
    , @NamedQuery(name = "Session.findByEndTime", query = "SELECT s FROM Session s WHERE s.endTime = :endTime")})
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
      @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SessionId", nullable = false)
    private Integer sessionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Date", nullable = false, length = 20)
    private String date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateNo", nullable = false)
    private int dateNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "StartTime", nullable = false, length = 5)
    private String startTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "EndTime", nullable = false, length = 5)
    private String endTime;
    @JoinColumn(name = "ClassId", referencedColumnName = "ClassId", nullable = false)
    @ManyToOne(optional = false)
    private Class classId;

    public Session() {
    }

    public Session(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Session(Integer sessionId, String date, int dateNo, String startTime, String endTime) {
        this.sessionId = sessionId;
        this.date = date;
        this.dateNo = dateNo;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDateNo() {
        return dateNo;
    }

    public void setDateNo(int dateNo) {
        this.dateNo = dateNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
@XmlTransient
    @JsonIgnore
    public Class getClassId() {
        return classId;
    }

    public void setClassId(Class classId) {
        this.classId = classId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sessionId != null ? sessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Session)) {
            return false;
        }
        Session other = (Session) object;
        if ((this.sessionId == null && other.sessionId != null) || (this.sessionId != null && !this.sessionId.equals(other.sessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "customer.model.Session[ sessionId=" + sessionId + " ]";
    }
    
}
