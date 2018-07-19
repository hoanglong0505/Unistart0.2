/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "ImageSchool")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImageSchool.findAll", query = "SELECT i FROM ImageSchool i")
    , @NamedQuery(name = "ImageSchool.findByImageId", query = "SELECT i FROM ImageSchool i WHERE i.imageId = :imageId")
    , @NamedQuery(name = "ImageSchool.findBySchoolCode", query = "SELECT i FROM ImageSchool i WHERE i.schoolCode = :schoolCode")
    , @NamedQuery(name = "ImageSchool.findByLink", query = "SELECT i FROM ImageSchool i WHERE i.link = :link")})
public class ImageSchool implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ImageId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    @Size(max = 50)
    @Column(name = "SchoolCode")
    private String schoolCode;
    @Size(max = 1073741823)
    @Column(name = "Link")
    private String link;

    public ImageSchool() {
    }

    public ImageSchool(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImageSchool)) {
            return false;
        }
        ImageSchool other = (ImageSchool) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ImageSchool[ imageId=" + imageId + " ]";
    }
    
}
