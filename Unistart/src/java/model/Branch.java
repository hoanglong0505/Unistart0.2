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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Branch", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b")
    , @NamedQuery(name = "Branch.findBySchoolId", query = "SELECT b FROM Branch b WHERE b.branchPK.schoolId = :schoolId")
    , @NamedQuery(name = "Branch.findByBranchNo", query = "SELECT b FROM Branch b WHERE b.branchPK.branchNo = :branchNo")
    , @NamedQuery(name = "Branch.findByBranchName", query = "SELECT b FROM Branch b WHERE b.branchName = :branchName")
    , @NamedQuery(name = "Branch.findByAddress", query = "SELECT b FROM Branch b WHERE b.address = :address")
    , @NamedQuery(name = "Branch.findByPhone", query = "SELECT b FROM Branch b WHERE b.phone = :phone")
    , @NamedQuery(name = "Branch.findByWebsite", query = "SELECT b FROM Branch b WHERE b.website = :website")})
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BranchPK branchPK;
    @Basic(optional = false)
    @Column(name = "BranchName", nullable = false, length = 100)
    private String branchName;
    @Column(name = "Address", length = 200)
    private String address;
    @Column(name = "Phone", length = 20)
    private String phone;
    @Column(name = "Website", length = 500)
    private String website;
    @JoinColumn(name = "LocationId", referencedColumnName = "LocationId")
    @ManyToOne
    private Location locationId;
    @JoinColumn(name = "SchoolId", referencedColumnName = "SchoolId", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private School school;

    public Branch() {
    }

    public Branch(BranchPK branchPK) {
        this.branchPK = branchPK;
    }

    public Branch(BranchPK branchPK, String branchName) {
        this.branchPK = branchPK;
        this.branchName = branchName;
    }

    public Branch(int schoolId, int branchNo) {
        this.branchPK = new BranchPK(schoolId, branchNo);
    }

    public BranchPK getBranchPK() {
        return branchPK;
    }

    public void setBranchPK(BranchPK branchPK) {
        this.branchPK = branchPK;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (branchPK != null ? branchPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Branch)) {
            return false;
        }
        Branch other = (Branch) object;
        if ((this.branchPK == null && other.branchPK != null) || (this.branchPK != null && !this.branchPK.equals(other.branchPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Branch[ branchPK=" + branchPK + " ]";
    }
    
}
