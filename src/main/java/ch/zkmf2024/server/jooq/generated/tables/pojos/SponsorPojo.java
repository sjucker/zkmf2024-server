/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.ISponsor;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class SponsorPojo implements ISponsor {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String type;
    private String name;
    private String cloudflareId;
    private String url;

    public SponsorPojo() {
    }

    public SponsorPojo(ISponsor value) {
        this.id = value.getId();
        this.type = value.getType();
        this.name = value.getName();
        this.cloudflareId = value.getCloudflareId();
        this.url = value.getUrl();
    }

    public SponsorPojo(
            Long id,
            String type,
            String name,
            String cloudflareId,
            String url
    ) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.cloudflareId = cloudflareId;
        this.url = url;
    }

    /**
     * Getter for <code>sponsor.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>sponsor.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>sponsor.type</code>.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Setter for <code>sponsor.type</code>.
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for <code>sponsor.name</code>.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>sponsor.name</code>.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>sponsor.cloudflare_id</code>.
     */
    @Override
    public String getCloudflareId() {
        return this.cloudflareId;
    }

    /**
     * Setter for <code>sponsor.cloudflare_id</code>.
     */
    @Override
    public void setCloudflareId(String cloudflareId) {
        this.cloudflareId = cloudflareId;
    }

    /**
     * Getter for <code>sponsor.url</code>.
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter for <code>sponsor.url</code>.
     */
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SponsorPojo other = (SponsorPojo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.type == null) {
            if (other.type != null)
                return false;
        } else if (!this.type.equals(other.type))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.cloudflareId == null) {
            if (other.cloudflareId != null)
                return false;
        } else if (!this.cloudflareId.equals(other.cloudflareId))
            return false;
        if (this.url == null) {
            if (other.url != null)
                return false;
        } else if (!this.url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.cloudflareId == null) ? 0 : this.cloudflareId.hashCode());
        result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SponsorPojo (");

        sb.append(id);
        sb.append(", ").append(type);
        sb.append(", ").append(name);
        sb.append(", ").append(cloudflareId);
        sb.append(", ").append(url);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ISponsor from) {
        setId(from.getId());
        setType(from.getType());
        setName(from.getName());
        setCloudflareId(from.getCloudflareId());
        setUrl(from.getUrl());
    }

    @Override
    public <E extends ISponsor> E into(E into) {
        into.from(this);
        return into;
    }
}
