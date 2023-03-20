/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IUser;

import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UserPojo implements IUser {

    private static final long serialVersionUID = 1L;

    private String email;
    private String role;
    private String password;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private String emailVerification;
    private LocalDateTime emailVerifiedAt;
    private String passwordResetToken;

    public UserPojo() {
    }

    public UserPojo(IUser value) {
        this.email = value.getEmail();
        this.role = value.getRole();
        this.password = value.getPassword();
        this.lastLogin = value.getLastLogin();
        this.createdAt = value.getCreatedAt();
        this.emailVerification = value.getEmailVerification();
        this.emailVerifiedAt = value.getEmailVerifiedAt();
        this.passwordResetToken = value.getPasswordResetToken();
    }

    public UserPojo(
            String email,
            String role,
            String password,
            LocalDateTime lastLogin,
            LocalDateTime createdAt,
            String emailVerification,
            LocalDateTime emailVerifiedAt,
            String passwordResetToken
    ) {
        this.email = email;
        this.role = role;
        this.password = password;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
        this.emailVerification = emailVerification;
        this.emailVerifiedAt = emailVerifiedAt;
        this.passwordResetToken = passwordResetToken;
    }

    /**
     * Getter for <code>user.email</code>.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>user.email</code>.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for <code>user.role</code>.
     */
    @Override
    public String getRole() {
        return this.role;
    }

    /**
     * Setter for <code>user.role</code>.
     */
    @Override
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter for <code>user.password</code>.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for <code>user.password</code>.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for <code>user.last_login</code>.
     */
    @Override
    public LocalDateTime getLastLogin() {
        return this.lastLogin;
    }

    /**
     * Setter for <code>user.last_login</code>.
     */
    @Override
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Getter for <code>user.created_at</code>.
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>user.created_at</code>.
     */
    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Getter for <code>user.email_verification</code>.
     */
    @Override
    public String getEmailVerification() {
        return this.emailVerification;
    }

    /**
     * Setter for <code>user.email_verification</code>.
     */
    @Override
    public void setEmailVerification(String emailVerification) {
        this.emailVerification = emailVerification;
    }

    /**
     * Getter for <code>user.email_verified_at</code>.
     */
    @Override
    public LocalDateTime getEmailVerifiedAt() {
        return this.emailVerifiedAt;
    }

    /**
     * Setter for <code>user.email_verified_at</code>.
     */
    @Override
    public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    /**
     * Getter for <code>user.password_reset_token</code>.
     */
    @Override
    public String getPasswordResetToken() {
        return this.passwordResetToken;
    }

    /**
     * Setter for <code>user.password_reset_token</code>.
     */
    @Override
    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserPojo other = (UserPojo) obj;
        if (this.email == null) {
            if (other.email != null)
                return false;
        } else if (!this.email.equals(other.email))
            return false;
        if (this.role == null) {
            if (other.role != null)
                return false;
        } else if (!this.role.equals(other.role))
            return false;
        if (this.password == null) {
            if (other.password != null)
                return false;
        } else if (!this.password.equals(other.password))
            return false;
        if (this.lastLogin == null) {
            if (other.lastLogin != null)
                return false;
        } else if (!this.lastLogin.equals(other.lastLogin))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.emailVerification == null) {
            if (other.emailVerification != null)
                return false;
        } else if (!this.emailVerification.equals(other.emailVerification))
            return false;
        if (this.emailVerifiedAt == null) {
            if (other.emailVerifiedAt != null)
                return false;
        } else if (!this.emailVerifiedAt.equals(other.emailVerifiedAt))
            return false;
        if (this.passwordResetToken == null) {
            if (other.passwordResetToken != null)
                return false;
        } else if (!this.passwordResetToken.equals(other.passwordResetToken))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.lastLogin == null) ? 0 : this.lastLogin.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.emailVerification == null) ? 0 : this.emailVerification.hashCode());
        result = prime * result + ((this.emailVerifiedAt == null) ? 0 : this.emailVerifiedAt.hashCode());
        result = prime * result + ((this.passwordResetToken == null) ? 0 : this.passwordResetToken.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserPojo (");

        sb.append(email);
        sb.append(", ").append(role);
        sb.append(", ").append(password);
        sb.append(", ").append(lastLogin);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(emailVerification);
        sb.append(", ").append(emailVerifiedAt);
        sb.append(", ").append(passwordResetToken);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IUser from) {
        setEmail(from.getEmail());
        setRole(from.getRole());
        setPassword(from.getPassword());
        setLastLogin(from.getLastLogin());
        setCreatedAt(from.getCreatedAt());
        setEmailVerification(from.getEmailVerification());
        setEmailVerifiedAt(from.getEmailVerifiedAt());
        setPasswordResetToken(from.getPasswordResetToken());
    }

    @Override
    public <E extends IUser> E into(E into) {
        into.from(this);
        return into;
    }
}
