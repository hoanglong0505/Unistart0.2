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
@Table(name = "FieldType", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FieldType.findAll", query = "SELECT f FROM FieldType f")
    , @NamedQuery(name = "FieldType.findByFieldTypeId", query = "SELECT f FROM FieldType f WHERE f.fieldTypeId = :fieldTypeId")
    , @NamedQuery(name = "FieldType.findByTypeName", query = "SELECT f FROM FieldType f WHERE f.typeName = :typeName")})
public class FieldType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FieldTypeId", nullable = false)
    private Integer fieldTypeId;
    @Basic(optional = false)
    @Column(name = "TypeName", nullable = false, length = 50)
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fieldTypeId")
    private List<Field> fieldList;

    public FieldType() {
    }

    public FieldType(Integer fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public FieldType(Integer fieldTypeId, String typeName) {
        this.fieldTypeId = fieldTypeId;
        this.typeName = typeName;
    }

    public Integer getFieldTypeId() {
        return fieldTypeId;
    }

    public void setFieldTypeId(Integer fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @XmlTransient
    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fieldTypeId != null ? fieldTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FieldType)) {
            return false;
        }
        FieldType other = (FieldType) object;
        if ((this.fieldTypeId == null && other.fieldTypeId != null) || (this.fieldTypeId != null && !this.fieldTypeId.equals(other.fieldTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FieldType[ fieldTypeId=" + fieldTypeId + " ]";
    }
    
}
