package com.testvagrant.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testvagrant.pojo.Player;
import com.testvagrant.pojo.Team;
import com.testvagrant.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TeamTest {
    static Team team;
    static RequestSpecification requestSpecification;
    static ConfigLoader configLoader;

    @BeforeClass
    public void beforeClass(){
        configLoader = ConfigLoader.getInstance();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(configLoader.getBaseUri());

        //Request Specification for the request
        requestSpecification = requestSpecBuilder.build();

        //fetching Team Data from server
        String teamData = given()
                .spec(requestSpecification)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .asString();

        // object mapper for converting json data into onjects using Pojo Clasess
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            team = objectMapper.readValue(teamData, Team.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testOnlyFourForeignPlayers(){
        int foreignPlayerCount = 0;
        List<Player> playerList = team.getPlayerList();
        for(Player player: playerList){
            if(!player.getCountry().equalsIgnoreCase("India")){
                foreignPlayerCount++;
            }
        }
        Assert.assertEquals(foreignPlayerCount, 4);
    }

    @Test
    public void atLeastOneWicketKeeperInTeam(){
        int wicketKeeperCount = 0;
        List<Player> playerList = team.getPlayerList();
        for(Player player: playerList){
            if(player.getRole().equals("Wicket-keeper")){
                wicketKeeperCount++;
            }
        }

        Assert.assertTrue(wicketKeeperCount>0);
    }
}
