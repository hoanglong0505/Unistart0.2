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
@Table(name = "School", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s")
    , @NamedQuery(name = "School.findBySchoolId", query = "SELECT s FROM School s WHERE s.schoolId = :schoolId")
    , @NamedQuery(name = "School.findBySchoolName", query = "SELECT s FROM School s WHERE s.schoolName = :schoolName")
    , @NamedQuery(name = "School.findBySchoolCode", query = "SELECT s FROM School s WHERE s.schoolCode = :schoolCode")
    , @NamedQuery(name = "School.findByWebsite", query = "SELECT s FROM School s WHERE s.website = :website")
    , @NamedQuery(name = "School.findByPhone", query = "SELECT s FROM School s WHERE s.phone = :phone")
    , @NamedQuery(name = "School.findByEmail", query = "SELECT s FROM School s WHERE s.email = :email")
    , @NamedQuery(name = "School.findByAvatar", query = "SELECT s FROM School s WHERE s.avatar = :avatar")
    , @NamedQuery(name = "School.findByAddress", query = "SELECT s FROM School s WHERE s.address = :address")})
public class School implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SchoolId", nullable = false)
    private Integer schoolId;
    @Basic(optional = false)
    @Column(name = "SchoolName", nullable = false, length = 100)
    private String schoolName;
    @Basic(optional = false)
    @Column(name = "SchoolCode", nullable = false, length = 50)
    private String schoolCode;
    @Column(name = "Website", length = 500)
    private String website;
    @Column(name = "Phone", length = 50)
    private String phone;
    @Column(name = "Email", length = 100)
    private String email;
    @Column(name = "Avatar", length = 500)
    private String avatar;
    @Column(name = "Address", length = 500)
    private String address;
    @OneToMany(mappedBy = "schoolId")
    private List<Rate> rateList;
    @OneToMany(mappedBy = "schoolId")
    private List<Article> articleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolId")
    private List<EntranceInfo> entranceInfoList;
    @JoinColumn(name = "TypeId", referencedColumnName = "TypeId")
    @ManyToOne
    private Type typeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    private List<Branch> branchList;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    @XmlTransient
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @XmlTransient
    public List<EntranceInfo> getEntranceInfoList() {
        return entranceInfoList;
    }

    public void setEntranceInfoList(List<EntranceInfo> entranceInfoList) {
        this.entranceInfoList = entranceInfoList;
    }

    public Type getTypeId() {
        return typeId;
    }

    public void setTypeId(Type typeId) {
        this.typeId = typeId;
    }

    @XmlTransient
    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
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
    
}
