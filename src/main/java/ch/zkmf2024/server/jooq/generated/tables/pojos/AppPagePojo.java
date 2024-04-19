/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IAppPage;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class AppPagePojo implements IAppPage {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String markdown;
    private String cloudflareId;
    private String title;
    private Boolean news;

    public AppPagePojo() {
    }

    public AppPagePojo(IAppPage value) {
        this.id = value.getId();
        this.markdown = value.getMarkdown();
        this.cloudflareId = value.getCloudflareId();
        this.title = value.getTitle();
        this.news = value.getNews();
    }

    public AppPagePojo(
            Long id,
            String markdown,
            String cloudflareId,
            String title,
            Boolean news
    ) {
        this.id = id;
        this.markdown = markdown;
        this.cloudflareId = cloudflareId;
        this.title = title;
        this.news = news;
    }

    /**
     * Getter for <code>app_page.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>app_page.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>app_page.markdown</code>.
     */
    @Override
    public String getMarkdown() {
        return this.markdown;
    }

    /**
     * Setter for <code>app_page.markdown</code>.
     */
    @Override
    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    /**
     * Getter for <code>app_page.cloudflare_id</code>.
     */
    @Override
    public String getCloudflareId() {
        return this.cloudflareId;
    }

    /**
     * Setter for <code>app_page.cloudflare_id</code>.
     */
    @Override
    public void setCloudflareId(String cloudflareId) {
        this.cloudflareId = cloudflareId;
    }

    /**
     * Getter for <code>app_page.title</code>.
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>app_page.title</code>.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for <code>app_page.news</code>.
     */
    @Override
    public Boolean getNews() {
        return this.news;
    }

    /**
     * Setter for <code>app_page.news</code>.
     */
    @Override
    public void setNews(Boolean news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AppPagePojo other = (AppPagePojo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.markdown == null) {
            if (other.markdown != null)
                return false;
        } else if (!this.markdown.equals(other.markdown))
            return false;
        if (this.cloudflareId == null) {
            if (other.cloudflareId != null)
                return false;
        } else if (!this.cloudflareId.equals(other.cloudflareId))
            return false;
        if (this.title == null) {
            if (other.title != null)
                return false;
        } else if (!this.title.equals(other.title))
            return false;
        if (this.news == null) {
            if (other.news != null)
                return false;
        } else if (!this.news.equals(other.news))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.markdown == null) ? 0 : this.markdown.hashCode());
        result = prime * result + ((this.cloudflareId == null) ? 0 : this.cloudflareId.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.news == null) ? 0 : this.news.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AppPagePojo (");

        sb.append(id);
        sb.append(", ").append(markdown);
        sb.append(", ").append(cloudflareId);
        sb.append(", ").append(title);
        sb.append(", ").append(news);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IAppPage from) {
        setId(from.getId());
        setMarkdown(from.getMarkdown());
        setCloudflareId(from.getCloudflareId());
        setTitle(from.getTitle());
        setNews(from.getNews());
    }

    @Override
    public <E extends IAppPage> E into(E into) {
        into.from(this);
        return into;
    }
}
