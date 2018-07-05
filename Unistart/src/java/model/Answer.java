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
import javax.persistence.Id;
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
@Table(name = "Answer", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a")
    , @NamedQuery(name = "Answer.findByAnswerId", query = "SELECT a FROM Answer a WHERE a.answerId = :answerId")
    , @NamedQuery(name = "Answer.findByAnswerDetail", query = "SELECT a FROM Answer a WHERE a.answerDetail = :answerDetail")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AnswerId", nullable = false)
    private Integer answerId;
    @Basic(optional = false)
    @Column(name = "AnswerDetail", nullable = false, length = 1073741823)
    private String answerDetail;
    @JoinColumn(name = "GenititeId", referencedColumnName = "GenitiveId", nullable = false)
    @ManyToOne(optional = false)
    private Genitite genititeId;
    @JoinColumn(name = "QuestionId", referencedColumnName = "QuestionId", nullable = false)
    @ManyToOne(optional = false)
    private Question questionId;

    public Answer() {
    }

    public Answer(Integer answerId) {
        this.answerId = answerId;
    }

    public Answer(Integer answerId, String answerDetail) {
        this.answerId = answerId;
        this.answerDetail = answerDetail;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public Genitite getGenititeId() {
        return genititeId;
    }

    public void setGenititeId(Genitite genititeId) {
        this.genititeId = genititeId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerId != null ? answerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerId == null && other.answerId != null) || (this.answerId != null && !this.answerId.equals(other.answerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Answer[ answerId=" + answerId + " ]";
    }
    
}
