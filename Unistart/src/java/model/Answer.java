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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "Answer.findByAnswerDetail", query = "SELECT a FROM Answer a WHERE a.answerDetail = :answerDetail")
    , @NamedQuery(name = "Answer.findByGenititeId", query = "SELECT a FROM Answer a WHERE a.answerPK.genititeId = :genititeId")
    , @NamedQuery(name = "Answer.findByQuestionId", query = "SELECT a FROM Answer a WHERE a.answerPK.questionId = :questionId")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AnswerPK answerPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "AnswerDetail", nullable = false, length = 1073741823)
    private String answerDetail;
    @JoinColumn(name = "GenititeId", referencedColumnName = "GenitiveId", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genitive genitive;
    @JoinColumn(name = "QuestionId", referencedColumnName = "QuestionId", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Question question;

    public Answer() {
    }

    public Answer(AnswerPK answerPK) {
        this.answerPK = answerPK;
    }

    public Answer(AnswerPK answerPK, String answerDetail) {
        this.answerPK = answerPK;
        this.answerDetail = answerDetail;
    }

    public Answer(int genititeId, int questionId) {
        this.answerPK = new AnswerPK(genititeId, questionId);
    }

    public AnswerPK getAnswerPK() {
        return answerPK;
    }

    public void setAnswerPK(AnswerPK answerPK) {
        this.answerPK = answerPK;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public Genitive getGenitive() {
        return genitive;
    }

    public void setGenitive(Genitive genitive) {
        this.genitive = genitive;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerPK != null ? answerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerPK == null && other.answerPK != null) || (this.answerPK != null && !this.answerPK.equals(other.answerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Answer[ answerPK=" + answerPK + " ]";
    }
    
}
