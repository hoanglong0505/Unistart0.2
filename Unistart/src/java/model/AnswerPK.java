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
 * @author Admin
 */
@Embeddable
public class AnswerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "GenititeId", nullable = false)
    private int genititeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QuestionId", nullable = false)
    private int questionId;

    public AnswerPK() {
    }

    public AnswerPK(int genititeId, int questionId) {
        this.genititeId = genititeId;
        this.questionId = questionId;
    }

    public int getGenititeId() {
        return genititeId;
    }

    public void setGenititeId(int genititeId) {
        this.genititeId = genititeId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) genititeId;
        hash += (int) questionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnswerPK)) {
            return false;
        }
        AnswerPK other = (AnswerPK) object;
        if (this.genititeId != other.genititeId) {
            return false;
        }
        if (this.questionId != other.questionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AnswerPK[ genititeId=" + genititeId + ", questionId=" + questionId + " ]";
    }
    
}
