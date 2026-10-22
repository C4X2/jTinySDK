package com.tinyurl.model.bulk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Request payload for submitting a bulk processing batch.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkRequest {

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("items")
    private List<BulkRequestItem> items;

    public BulkRequest() {
    }

    public BulkRequest(List<BulkRequestItem> items) {
        this.items = items;
    }

    public BulkRequest(String domain, List<BulkRequestItem> items) {
        this.domain = domain;
        this.items = items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<BulkRequestItem> getItems() {
        return items;
    }

    public void setItems(List<BulkRequestItem> items) {
        this.items = items;
    }

    public static class Builder {
        private String domain;
        private List<BulkRequestItem> items = new ArrayList<>();

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder items(List<BulkRequestItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(BulkRequestItem item) {
            if (this.items == null) {
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return this;
        }

        public Builder addItems(BulkRequestItem... items) {
            if (this.items == null) {
                this.items = new ArrayList<>();
            }
            this.items.addAll(Arrays.asList(items));
            return this;
        }

        public BulkRequest build() {
            if (items == null || items.isEmpty()) {
                throw new IllegalArgumentException("items must not be null or empty");
            }
            return new BulkRequest(domain, items);
        }
    }
}
