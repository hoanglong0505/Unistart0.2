/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TNT
 */
@Embeddable
public class BranchPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SchoolId")
    private int schoolId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BranchNo")
    private int branchNo;

    public BranchPK() {
    }

    public BranchPK(int schoolId, int branchNo) {
        this.schoolId = schoolId;
        this.branchNo = branchNo;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) schoolId;
        hash += (int) branchNo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BranchPK)) {
            return false;
        }
        BranchPK other = (BranchPK) object;
        if (this.schoolId != other.schoolId) {
            return false;
        }
        if (this.branchNo != other.branchNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.BranchPK[ schoolId=" + schoolId + ", branchNo=" + branchNo + " ]";
    }
    
}
