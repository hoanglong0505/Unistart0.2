/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knowledge.model;

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
@Table(name = "Chapter", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chapter.findAll", query = "SELECT c FROM Chapter c")
    , @NamedQuery(name = "Chapter.findByChapterId", query = "SELECT c FROM Chapter c WHERE c.chapterId = :chapterId")
    , @NamedQuery(name = "Chapter.findByChapterName", query = "SELECT c FROM Chapter c WHERE c.chapterName = :chapterName")})
public class Chapter implements Serializable {

    @Size(max = 50)
    @Column(name = "Type", length = 50)
    private String type;

    @Column(name = "no")
    private Integer no;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ChapterId", nullable = false)
    private Integer chapterId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ChapterName", nullable = false, length = 1000)
    private String chapterName;
    @JoinColumn(name = "GradeId", referencedColumnName = "GradeId", nullable = false)
    @ManyToOne(optional = false)
    private Grade gradeId;
    @JoinColumn(name = "SubjectId", referencedColumnName = "SubjectId", nullable = false)
    @ManyToOne(optional = false)
    private Subject subjectId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapterId")
    private List<Unit> unitList;

    public Chapter() {
    }

    public Chapter(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Chapter(Integer chapterId, String chapterName) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Grade getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grade gradeId) {
        this.gradeId = gradeId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }


    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chapterId != null ? chapterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chapter)) {
            return false;
        }
        Chapter other = (Chapter) object;
        if ((this.chapterId == null && other.chapterId != null) || (this.chapterId != null && !this.chapterId.equals(other.chapterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Knowledge.model.Chapter[ chapterId=" + chapterId + " ]";
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
