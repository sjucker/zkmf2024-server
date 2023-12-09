/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IImage;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class ImagePojo implements IImage {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long foreignKey;
    private byte[] content;
    private String name;
    private LocalDateTime uploadedAt;
    private String type;
    private String cloudflareId;

    public ImagePojo() {
    }

    public ImagePojo(IImage value) {
        this.id = value.getId();
        this.foreignKey = value.getForeignKey();
        this.content = value.getContent();
        this.name = value.getName();
        this.uploadedAt = value.getUploadedAt();
        this.type = value.getType();
        this.cloudflareId = value.getCloudflareId();
    }

    public ImagePojo(
            Long id,
            Long foreignKey,
            byte[] content,
            String name,
            LocalDateTime uploadedAt,
            String type,
            String cloudflareId
    ) {
        this.id = id;
        this.foreignKey = foreignKey;
        this.content = content;
        this.name = name;
        this.uploadedAt = uploadedAt;
        this.type = type;
        this.cloudflareId = cloudflareId;
    }

    /**
     * Getter for <code>image.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>image.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>image.foreign_key</code>.
     */
    @Override
    public Long getForeignKey() {
        return this.foreignKey;
    }

    /**
     * Setter for <code>image.foreign_key</code>.
     */
    @Override
    public void setForeignKey(Long foreignKey) {
        this.foreignKey = foreignKey;
    }

    /**
     * Getter for <code>image.content</code>.
     */
    @Override
    public byte[] getContent() {
        return this.content;
    }

    /**
     * Setter for <code>image.content</code>.
     */
    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * Getter for <code>image.name</code>.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>image.name</code>.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>image.uploaded_at</code>.
     */
    @Override
    public LocalDateTime getUploadedAt() {
        return this.uploadedAt;
    }

    /**
     * Setter for <code>image.uploaded_at</code>.
     */
    @Override
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    /**
     * Getter for <code>image.type</code>.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Setter for <code>image.type</code>.
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for <code>image.cloudflare_id</code>.
     */
    @Override
    public String getCloudflareId() {
        return this.cloudflareId;
    }

    /**
     * Setter for <code>image.cloudflare_id</code>.
     */
    @Override
    public void setCloudflareId(String cloudflareId) {
        this.cloudflareId = cloudflareId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ImagePojo other = (ImagePojo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.foreignKey == null) {
            if (other.foreignKey != null)
                return false;
        } else if (!this.foreignKey.equals(other.foreignKey))
            return false;
        if (this.content == null) {
            if (other.content != null)
                return false;
        } else if (!Arrays.equals(this.content, other.content))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.uploadedAt == null) {
            if (other.uploadedAt != null)
                return false;
        } else if (!this.uploadedAt.equals(other.uploadedAt))
            return false;
        if (this.type == null) {
            if (other.type != null)
                return false;
        } else if (!this.type.equals(other.type))
            return false;
        if (this.cloudflareId == null) {
            if (other.cloudflareId != null)
                return false;
        } else if (!this.cloudflareId.equals(other.cloudflareId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.foreignKey == null) ? 0 : this.foreignKey.hashCode());
        result = prime * result + ((this.content == null) ? 0 : Arrays.hashCode(this.content));
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.uploadedAt == null) ? 0 : this.uploadedAt.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.cloudflareId == null) ? 0 : this.cloudflareId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ImagePojo (");

        sb.append(id);
        sb.append(", ").append(foreignKey);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(name);
        sb.append(", ").append(uploadedAt);
        sb.append(", ").append(type);
        sb.append(", ").append(cloudflareId);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IImage from) {
        setId(from.getId());
        setForeignKey(from.getForeignKey());
        setContent(from.getContent());
        setName(from.getName());
        setUploadedAt(from.getUploadedAt());
        setType(from.getType());
        setCloudflareId(from.getCloudflareId());
    }

    @Override
    public <E extends IImage> E into(E into) {
        into.from(this);
        return into;
    }
}
