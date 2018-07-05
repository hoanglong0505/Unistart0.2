/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TNT
 */
@Entity
@Table(name = "Field")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Field.findAll", query = "SELECT f FROM Field f")
    , @NamedQuery(name = "Field.findByFieldId", query = "SELECT f FROM Field f WHERE f.fieldId = :fieldId")
    , @NamedQuery(name = "Field.findByFieldCode", query = "SELECT f FROM Field f WHERE f.fieldCode = :fieldCode")
    , @NamedQuery(name = "Field.findByFieldName", query = "SELECT f FROM Field f WHERE f.fieldName = :fieldName")})
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FieldId")
    private Integer fieldId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FieldCode")
    private String fieldCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FieldName")
    private String fieldName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fieldId")
    private Collection<EntranceInfo> entranceInfoCollection;
    @OneToMany(mappedBy = "preFieldId")
    private Collection<Field> fieldCollection;
    @JoinColumn(name = "PreFieldId", referencedColumnName = "FieldId")
    @ManyToOne
    private Field preFieldId;
    @JoinColumn(name = "FieldTypeId", referencedColumnName = "FieldTypeId")
    @ManyToOne(optional = false)
    private FieldType fieldTypeId;

    public Field() {
    }

    public Field(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Field(Integer fieldId, String fieldCode, String fieldName) {
        this.fieldId = fieldId;
        this.fieldCode = fieldCode;
        this.fieldName = fieldName;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @XmlTransient
    public Collection<EntranceInfo> getEntranceInfoCollection() {
        return entranceInfoCollection;
    }

    public void setEntranceInfoCollection(Collection<EntranceInfo> entranceInfoCollection) {
        this.entranceInfoCollection = entranceInfoCollection;
    }

    @XmlTransient
    public Collection<Field> getFieldCollection() {
        return fieldCollection;
    }

    public void setFieldCollection(Collection<Field> fieldCollection) {
        this.fieldCollection = fieldCollection;
    }

    public Field getPreFieldId() {
        return preFieldId;
    }

    public void setPreFieldId(Field preFieldId) {
        this.preFieldId = preFieldId;
    }

    public FieldType getFieldTypeId() {
        return fieldTypeId;
    }

    public void setFieldTypeId(FieldType fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fieldId != null ? fieldId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Field)) {
            return false;
        }
        Field other = (Field) object;
        if ((this.fieldId == null && other.fieldId != null) || (this.fieldId != null && !this.fieldId.equals(other.fieldId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Field[ fieldId=" + fieldId + " ]";
    }
    
}
