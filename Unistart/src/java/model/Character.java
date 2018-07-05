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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Character", catalog = "unistart2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Character.findAll", query = "SELECT c FROM Character c")
    , @NamedQuery(name = "Character.findByCharacterId", query = "SELECT c FROM Character c WHERE c.characterId = :characterId")
    , @NamedQuery(name = "Character.findByCharacterCode", query = "SELECT c FROM Character c WHERE c.characterCode = :characterCode")
    , @NamedQuery(name = "Character.findByCharacterContent", query = "SELECT c FROM Character c WHERE c.characterContent = :characterContent")
    , @NamedQuery(name = "Character.findByCharacterName", query = "SELECT c FROM Character c WHERE c.characterName = :characterName")})
public class Character implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CharacterId", nullable = false)
    private Integer characterId;
    @Basic(optional = false)
    @Column(name = "CharacterCode", nullable = false, length = 5)
    private String characterCode;
    @Basic(optional = false)
    @Column(name = "CharacterContent", nullable = false, length = 1073741823)
    private String characterContent;
    @Basic(optional = false)
    @Column(name = "CharacterName", nullable = false, length = 500)
    private String characterName;

    public Character() {
    }

    public Character(Integer characterId) {
        this.characterId = characterId;
    }

    public Character(Integer characterId, String characterCode, String characterContent, String characterName) {
        this.characterId = characterId;
        this.characterCode = characterCode;
        this.characterContent = characterContent;
        this.characterName = characterName;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getCharacterCode() {
        return characterCode;
    }

    public void setCharacterCode(String characterCode) {
        this.characterCode = characterCode;
    }

    public String getCharacterContent() {
        return characterContent;
    }

    public void setCharacterContent(String characterContent) {
        this.characterContent = characterContent;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (characterId != null ? characterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Character)) {
            return false;
        }
        Character other = (Character) object;
        if ((this.characterId == null && other.characterId != null) || (this.characterId != null && !this.characterId.equals(other.characterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Character[ characterId=" + characterId + " ]";
    }
    
}
