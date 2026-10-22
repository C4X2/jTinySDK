package com.tinyurl.model;

import java.util.Objects;

/**
 * Filter parameters for counting TinyURL aliases.
 */
public class GetUrlsCountRequest {

    private UrlsType type = UrlsType.AVAILABLE;
    private String from;
    private String to;
    private String search;

    public GetUrlsCountRequest() {
    }

    public GetUrlsCountRequest(UrlsType type) {
        this.type = type != null ? type : UrlsType.AVAILABLE;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UrlsType getType() {
        return type;
    }

    public void setType(UrlsType type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public static class Builder {
        private UrlsType type = UrlsType.AVAILABLE;
        private String from;
        private String to;
        private String search;

        public Builder type(UrlsType type) {
            this.type = type;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder search(String search) {
            this.search = search;
            return this;
        }

        public Builder searchByAlias(String alias) {
            this.search = "alias:" + Objects.requireNonNull(alias, "alias must not be null");
            return this;
        }

        public Builder searchByTag(String tag) {
            this.search = "tag:" + Objects.requireNonNull(tag, "tag must not be null");
            return this;
        }

        public GetUrlsCountRequest build() {
            GetUrlsCountRequest req = new GetUrlsCountRequest();
            req.setType(type != null ? type : UrlsType.AVAILABLE);
            req.setFrom(from);
            req.setTo(to);
            req.setSearch(search);
            return req;
        }
    }
}
