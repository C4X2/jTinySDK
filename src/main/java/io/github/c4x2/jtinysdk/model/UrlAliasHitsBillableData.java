package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Detailed TinyURL alias data including target URL, click hits, and billable user/team info.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlAliasHitsBillableData extends UrlAliasData {

    @JsonProperty("user")
    private UserInfo user;

    @JsonProperty("team")
    private TeamInfo team;

    @JsonProperty("hits")
    private Integer hits;

    public UrlAliasHitsBillableData() {
        super();
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public TeamInfo getTeam() {
        return team;
    }

    public void setTeam(TeamInfo team) {
        this.team = team;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UrlAliasHitsBillableData that = (UrlAliasHitsBillableData) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(team, that.team) &&
                Objects.equals(hits, that.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, team, hits);
    }

    @Override
    public String toString() {
        return "UrlAliasHitsBillableData{" +
                "tinyUrl='" + getTinyUrl() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", domain='" + getDomain() + '\'' +
                ", alias='" + getAlias() + '\'' +
                ", hits=" + hits +
                ", user=" + user +
                ", team=" + team +
                ", deleted=" + getDeleted() +
                ", archived=" + getArchived() +
                ", tags=" + getTags() +
                ", analytics=" + getAnalytics() +
                ", createdAt='" + getCreatedAt() + '\'' +
                ", expiresAt='" + getExpiresAt() + '\'' +
                '}';
    }
}

