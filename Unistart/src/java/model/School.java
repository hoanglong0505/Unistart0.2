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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "School")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s")
    , @NamedQuery(name = "School.findBySchoolId", query = "SELECT s FROM School s WHERE s.schoolId = :schoolId")
    , @NamedQuery(name = "School.findBySchoolName", query = "SELECT s FROM School s WHERE s.schoolName = :schoolName")
    , @NamedQuery(name = "School.findBySchoolCode", query = "SELECT s FROM School s WHERE s.schoolCode = :schoolCode")
    , @NamedQuery(name = "School.findByWebsite", query = "SELECT s FROM School s WHERE s.website = :website")
    , @NamedQuery(name = "School.findByPhone", query = "SELECT s FROM School s WHERE s.phone = :phone")
    , @NamedQuery(name = "School.findByEmail", query = "SELECT s FROM School s WHERE s.email = :email")
    , @NamedQuery(name = "School.findByAvatar", query = "SELECT s FROM School s WHERE s.avatar = :avatar")})
public class School implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SchoolId")
    private Integer schoolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SchoolName")
    private String schoolName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SchoolCode")
    private String schoolCode;
    @Size(max = 500)
    @Column(name = "Website")
    private String website;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "Email")
    private String email;
    @Size(max = 500)
    @Column(name = "Avatar")
    private String avatar;

    @OneToMany(mappedBy = "schoolId")
    private Collection<Article> articleCollection;

    @JoinColumn(name = "TypeId", referencedColumnName = "TypeId")
    @ManyToOne
    private Type type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    private Collection<AverageRate> averageRateCollection;

    public School() {
    }

    public School(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public School(Integer schoolId, String schoolName, String schoolCode) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolCode = schoolCode;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @XmlTransient
    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        hash += (schoolId != null ? schoolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.schoolId == null && other.schoolId != null) || (this.schoolId != null && !this.schoolId.equals(other.schoolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.School[ schoolId=" + schoolId + " ]";
    }

    //============== RELATIONSHIP HANDLER ===============
    //RATES
    @XmlTransient
    @Transient
    public int ratesHandler = Constants.TRANSIENT;

    @OneToMany(mappedBy = "school")
    private Collection<Rate> rates;

    public Collection<Rate> getRates() {
        if (ratesHandler == Constants.GENERATE) {
            setRatesBiDir(Constants.TRANSIENT);
            List<Rate> orderRates = new ArrayList(rates);
            Collections.sort(orderRates, new Comparator<Rate>() {
                @Override
                public int compare(Rate o2, Rate o1) {
                    Date o1d = o1.getSubmitDate();
                    Date o2d = o2.getSubmitDate();

                    if (o1d == null || o2d == null) {
                        if (o1d == null && o2d != null) {
                            return -1;
                        }
                        if (o1d != null && o2d == null) {
                            return 1;
                        }
                        return 0;
                    }

                    return o1d.compareTo(o2d);
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
            r.schoolHandler = MODE;
        }
    }

    //---------------------------------
    //ENTRANCE INFOS
    @XmlTransient
    @Transient
    public int eInfosHandler = Constants.TRANSIENT;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    private Collection<EntranceInfo> entranceInfos;

    public Collection<EntranceInfo> getEntranceInfos() {
        if (eInfosHandler == Constants.GENERATE) {
            setEInfosBiDir(Constants.TRANSIENT);
            return entranceInfos;
        }
        return null;
    }

    public void setEntranceInfos(Collection<EntranceInfo> entranceInfos) {
        this.entranceInfos = entranceInfos;
    }

    public void setEInfosBiDir(int MODE) {
        for (EntranceInfo eI : entranceInfos) {
            eI.schoolHandler = MODE;
        }
    }

    //---------------------------------
    //BRANCHS
    @XmlTransient
    @Transient
    public int branchsHandler = Constants.TRANSIENT;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    private Collection<Branch> branchs;

    public Collection<Branch> getBranchs() {
        if (branchsHandler == Constants.GENERATE) {
            setBranchsBiDir(Constants.TRANSIENT);
            return branchs;
        }
        return null;
    }

    public void setBranchs(Collection<Branch> branchs) {
        this.branchs = branchs;
    }

    public void setBranchsBiDir(int MODE) {
        for (Branch b : branchs) {
            b.schoolHandler = MODE;
        }
    }

    //---------------------------------
}
