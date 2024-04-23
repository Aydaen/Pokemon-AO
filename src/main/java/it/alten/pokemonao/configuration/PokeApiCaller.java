package it.alten.pokemonao.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.alten.pokemonao.database.entity.TypeEntity;
import it.alten.pokemonao.database.repository.TypeRepository;
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

    private final TypeRepository typeRepository;
    private static final String PLACEHOLDER_ICON_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/0.png";
    private static final String POKE_API_URL = "https://pokeapi.co/api/v2/";

    private static final String TYPE_POKE_API_URL = "type/";
    private static final String AMAZON_S3_URL = "https://99tvpecyz4.execute-api.eu-west-3.amazonaws.com/v1/pokemon-type-images/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws Exception {
        fetchTypeFromPokeAPI();
    }

    private void fetchTypeFromPokeAPI() throws JsonProcessingException {
        for (int i = 1; i <= 18; i++) {
            TypeEntity typeEntity = new TypeEntity();
            String typeUrl = POKE_API_URL + TYPE_POKE_API_URL + i;
            String data = restTemplate.getForEntity(typeUrl, String.class).getBody();

            typeEntity.setId(i);

            JsonNode root = objectMapper.readTree(data);

            int idAPI = root.at("/id").asInt();
            typeEntity.setTypeApiId(idAPI);

            String nameAPI = root.at("/name").asText();
            typeEntity.setName(nameAPI);

            String iconS3 = getTypeIconByTypeName(nameAPI);
            typeEntity.setIcon(iconS3);

            typeRepository.save(typeEntity);
        }
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
    //Specie del pokemon
    public String getPokemonPokeApiId(Integer pokemonPokeApiId){
        String pokemonUrl = POKE_API_URL + "pokemon/" + pokemonPokeApiId;
        try{
            return getJsonFromResponse(pokemonUrl).get("name").asText();
        } catch (ResourceAccessException ex){
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //HP di base
    public Integer getMaxHpPokeApiId(Integer pokemonPokeApiId){
        String pokemonUrl = POKE_API_URL + "pokemon/" + pokemonPokeApiId;
        try{
            return getJsonFromResponse(pokemonUrl).at("/stats/0/base_stat").asInt();
        } catch (ResourceAccessException ex){
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //Nome del tipo
    public String getTypeNameByPokeApiId(Integer id){
        String typeUrl = POKE_API_URL + "type/" + id;
        try{
            return getJsonFromResponse(typeUrl).get("name").asText();
        } catch (ResourceAccessException ex){
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //Icona del tipo
    public String getTypeIconByTypeName(String typeName){
        return AMAZON_S3_URL + typeName + "Type.png";
    }

    //Nomi delle mosse
    public List<String> getMovesNamePokeByApiId(List<Integer> moveIds){
        List<String> moveNames = new ArrayList<>();
        String moveUrl = POKE_API_URL + "move/";
        for(Integer moveId : moveIds){
            try{
                JsonNode jsonFile = getJsonFromResponse(moveUrl + moveId);
                moveNames.add(jsonFile.get("name").asText());
            } catch (ResourceAccessException ex){
                moveNames.add(null);
            } catch (Exception e){
                moveNames.add(null);
                e.printStackTrace();
            }
        }
        return moveNames;
    }

    //Potenza delle mosse
    public List<Integer> getMovesPowerPokeByApiId(List<Integer> moveIds){
        List<Integer> movePowers = new ArrayList<>();
        String moveUrl = POKE_API_URL + "move/";
        for(Integer moveId : moveIds){
            try{
                JsonNode jsonFile = getJsonFromResponse(moveUrl + moveId);
                movePowers.add(jsonFile.get("power").asInt());
            } catch (ResourceAccessException ex){
                movePowers.add(null);
            } catch (Exception e){
                movePowers.add(null);
                e.printStackTrace();
            }
        }
        return movePowers;
    }



    private JsonNode getJsonFromResponse(String url) throws ResourceAccessException, JsonProcessingException{
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String data = response.getBody();
        return objectMapper.readTree(data);
    }

}
