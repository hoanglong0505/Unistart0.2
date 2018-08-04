/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knowledge.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Unit", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unit.findAll", query = "SELECT u FROM Unit u")
    , @NamedQuery(name = "Unit.findByUnitId", query = "SELECT u FROM Unit u WHERE u.unitId = :unitId")
    , @NamedQuery(name = "Unit.findByUnitName", query = "SELECT u FROM Unit u WHERE u.unitName = :unitName")
    , @NamedQuery(name = "Unit.findByKnowledge", query = "SELECT u FROM Unit u WHERE u.knowledge = :knowledge")})
public class Unit implements Serializable {

    @Column(name = "No")
    private Integer no;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UnitId", nullable = false)
    private Integer unitId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "UnitName", nullable = false, length = 1000)
    private String unitName;
    @Size(max = 1000)
    @Column(name = "Knowledge", length = 1000)
    private String knowledge;
    @JoinColumn(name = "ChapterId", referencedColumnName = "ChapterId", nullable = false)
    @ManyToOne(optional = false)
    private Chapter chapterId;

    public Unit() {
    }

    public Unit(Integer unitId) {
        this.unitId = unitId;
    }

    public Unit(Integer unitId, String unitName) {
        this.unitId = unitId;
        this.unitName = unitName;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }
    @XmlTransient
    @JsonIgnore
    public Chapter getChapterId() {
        return chapterId;
    }

    public void setChapterId(Chapter chapterId) {
        this.chapterId = chapterId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitId != null ? unitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) object;
        if ((this.unitId == null && other.unitId != null) || (this.unitId != null && !this.unitId.equals(other.unitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Knowledge.model.Unit[ unitId=" + unitId + " ]";
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
    
}
