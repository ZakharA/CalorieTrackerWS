/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrackerws;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zakhar
 */
@Entity
@Table(name = "CREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
    , @NamedQuery(name = "Credential.findByCredentialId", query = "SELECT c FROM Credential c WHERE c.credentialId = :credentialId")
    , @NamedQuery(name = "Credential.findByUsername", query = "SELECT c FROM Credential c WHERE c.username = :username")
    , @NamedQuery(name = "Credential.findByPasswordHash", query = "SELECT c FROM Credential c WHERE c.passwordHash = :passwordHash")
    , @NamedQuery(name = "Credential.findBySignUpDate", query = "SELECT c FROM Credential c WHERE c.signUpDate = :signUpDate")})
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREDENTIAL_ID")
    private Integer credentialId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "PASSWORD_HASH")
    private String passwordHash;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGN_UP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date signUpDate;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private AppUser userId;

    public Credential() {
    }

    public Credential(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public Credential(Integer credentialId, String username, String passwordHash, Date signUpDate) {
        this.credentialId = credentialId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.signUpDate = signUpDate;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public AppUser getUserId() {
        return userId;
    }

    public void setUserId(AppUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (credentialId != null ? credentialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.credentialId == null && other.credentialId != null) || (this.credentialId != null && !this.credentialId.equals(other.credentialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ctrackerws.Credential[ credentialId=" + credentialId + " ]";
    }
    
}
