package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Billable user and team data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillableData {

    @JsonProperty("user")
    private UserInfo user;

    @JsonProperty("team")
    private TeamInfo team;

    public BillableData() {
    }

    public BillableData(UserInfo user, TeamInfo team) {
        this.user = user;
        this.team = team;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillableData that = (BillableData) o;
        return Objects.equals(user, that.user) && Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, team);
    }

    @Override
    public String toString() {
        return "BillableData{" +
                "user=" + user +
                ", team=" + team +
                '}';
    }
}
