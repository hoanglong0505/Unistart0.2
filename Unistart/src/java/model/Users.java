/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import app.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import restful.RateFacadeREST;

/**
 *
 * @author TNT
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name")
    , @NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UserId")
    private String userId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "Email")
    private String email;
    @Size(max = 200)
    @Column(name = "Name")
    private String name;
    @Size(max = 500)
    @Column(name = "Avatar")
    private String avatar;

    public Users() {
    }

    public Users(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @XmlTransient
    @Transient
    private String idToken;

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Users[ userId=" + userId + " ]";
    }

    //================= RELATIONSHIP HANDLER ===============
    //Rates
    @XmlTransient
    @Transient
    public int ratesHandler = Constants.TRANSIENT;

    @XmlTransient
    @Transient
    public boolean hideRate = false;

    @OneToMany(mappedBy = "user")
    private Collection<Rate> rates;

    public Collection<Rate> getRates() {
        if (ratesHandler == Constants.GENERATE) {
            setRatesBiDir(Constants.TRANSIENT);
            setRatesAverageValue();
            if (hideRate) {
                rates = hideAnonymousRates();
            }
            List<Rate> orderRates = new ArrayList(rates);
            Collections.sort(orderRates, new Comparator<Rate>() {
                @Override
                public int compare(Rate o1, Rate o2) {
                    Date o1d = o1.getSubmitDate();
                    Date o2d = o2.getSubmitDate();

                    int result = o2d.compareTo(o1d);
                    if (result == 0) {
                        return o2.getSubmitTime().compareTo(o1.getSubmitTime());
                    }
                    return result;
                }
            });
            return orderRates;
        }
        return null;
    }

    public void setRates(Collection<Rate> rates) {
        this.rates = rates;
    }

    public void setRatesBiDir(int MODE) {
        for (Rate r : rates) {
            r.userHandler = MODE;
            r.setSchoolBiDir(MODE);
            r.getSchool().eInfosHandler = MODE;
            r.getSchool().branchsHandler = MODE;
        }
    }

    //---------------------------------
    //Reports
    @XmlTransient
    @Transient
    public int reportsHandler = Constants.TRANSIENT;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Report> reports;

    public Collection<Report> getReports() {
        if (ratesHandler == Constants.GENERATE) {
            setReportsBiDir(Constants.TRANSIENT);
            return reports;
        }
        return null;
    }

    public void setReports(Collection<Report> reports) {
        this.reports = reports;
    }

    public void setReportsBiDir(int MODE) {
        for (Report rp : reports) {
            rp.userHandler = MODE;
        }
    }

    //-----------------------------------
    public void setRatesAverageValue() {
        RateFacadeREST rRest = new RateFacadeREST();
        for (Rate r : rates) {
            rRest.setRateAverageValue(r);
        }
    }

    public List<Rate> hideAnonymousRates() {
        List<Rate> filterRates = new ArrayList();
        for (Rate r : rates) {
            if (!r.getAnonymous()) {
                filterRates.add(r);
            }
        }
        return filterRates;
    }

}
