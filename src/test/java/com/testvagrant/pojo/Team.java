package com.testvagrant.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("playerList")
    private List<Player> playerList = null;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }
    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("playerList")
    public List<Player> getPlayerList() {
        return playerList;
    }
    @JsonProperty("playerList")
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
