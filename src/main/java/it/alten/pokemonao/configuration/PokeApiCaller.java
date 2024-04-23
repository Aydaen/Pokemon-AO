package it.alten.pokemonao.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PokeApiCaller implements CommandLineRunner {
    private static final String PLACEHOLDER_ICON_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/0.png";
    private static final String POKE_API_URL = "https://pokeapi.co/api/v2/";
    private static final String AMAZON_S3_URL = "https://99tvpecyz4.execute-api.eu-west-3.amazonaws.com/v1/pokemon-type-images/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(getMovesPowerPokeByApiId(List.of(1,2,3)));
    }

    //Immagine del pokemon
    public String getSpriteByApiId(Integer pokemonPokeApiId){
        String url = POKE_API_URL + "pokemon/" + pokemonPokeApiId;
        try{
            return getJsonFromResponse(url).at("/sprites/front_default").asText();
        } catch (ResourceAccessException ex){
            return PLACEHOLDER_ICON_URL;
        } catch (Exception e){
            e.printStackTrace();
            return PLACEHOLDER_ICON_URL;
        }
    }
    //Specie del pokemon (non dovrebbe servire)
    public Integer getPokemonPokeApiId(Integer pokemonPokeApiId) throws JsonProcessingException {
        String pokemonUrl = POKE_API_URL + "pokemon/" + pokemonPokeApiId;
        return getJsonFromResponse(pokemonUrl).get("id").asInt();
    }

    //HP di base
    public Integer getMaxHpPokeApiId(Integer pokemonPokeApiId) throws JsonProcessingException{
        String pokemonUrl = POKE_API_URL + "pokemon/" + pokemonPokeApiId;
        return getJsonFromResponse(pokemonUrl).at("/stats/0/base_stat").asInt();
    }

    //Nome del tipo
    public String getTypeNameByPokeApiId(Integer id) throws JsonProcessingException {
        String typeUrl = POKE_API_URL + "type/" + id;
        return getJsonFromResponse(typeUrl).get("name").asText();
    }

    //Icona del tipo
    public String getTypeIconByTypeName(String typeName){
        return AMAZON_S3_URL + typeName + "Type.png";
    }

    //Nomi delle mosse
    public List<String> getMovesNamePokeByApiId(List<Integer> moveIds) throws JsonProcessingException{
        List<String> moveNames = new ArrayList<>();
        String moveUrl = POKE_API_URL + "move/";
        for(Integer moveId : moveIds){
            JsonNode jsonFile = getJsonFromResponse(moveUrl + moveId);
            moveNames.add(jsonFile.get("name").asText());
        }
        return moveNames;
    }

    //Potenza delle mosse
    public List<Integer> getMovesPowerPokeByApiId(List<Integer> moveIds) throws JsonProcessingException{
        List<Integer> movePowers = new ArrayList<>();
        String moveUrl = POKE_API_URL + "move/";
        for(Integer moveId : moveIds){
            JsonNode jsonFile = getJsonFromResponse(moveUrl + moveId);
            movePowers.add(jsonFile.get("power").asInt());
        }
        return movePowers;
    }



    private JsonNode getJsonFromResponse(String url) throws ResourceAccessException, JsonProcessingException{
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String data = response.getBody();
        return objectMapper.readTree(data);
    }

}
