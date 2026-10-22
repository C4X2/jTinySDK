package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Team account information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamInfo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    public TeamInfo() {
    }

    public TeamInfo(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamInfo teamInfo = (TeamInfo) o;
        return Objects.equals(name, teamInfo.name) && Objects.equals(slug, teamInfo.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, slug);
    }

    @Override
    public String toString() {
        return "TeamInfo{" +
                "name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
