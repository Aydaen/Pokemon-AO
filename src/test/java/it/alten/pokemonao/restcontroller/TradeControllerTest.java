package it.alten.pokemonao.restcontroller;


import it.alten.pokemonao.controllers.TradeController;
import it.alten.pokemonao.database.entity.PokemonEntity;
import it.alten.pokemonao.database.repository.PokemonRepository;
import it.alten.pokemonao.dtos.MoveDTO;
import it.alten.pokemonao.dtos.PokemonDTO;
import it.alten.pokemonao.dtos.TypeDTO;
import it.alten.pokemonao.testdbconfig.TestDatabaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Import(TestDatabaseConfig.class)
class TradeControllerTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TradeController tradeController;

    @Mock
    private ModelMapper mapper;

    private PokemonDTO pokemonToTradeDTO;
    private PokemonDTO receivedPokemonDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new ModelMapper();

        // Crea un mock del PokemonDTO da inviare
        pokemonToTradeDTO = new PokemonDTO();
        pokemonToTradeDTO.setId(1);
        pokemonToTradeDTO.setName("charizard");
        pokemonToTradeDTO.setSprite(null);
        pokemonToTradeDTO.setCurrentHp(100);
        pokemonToTradeDTO.setMaxHp(200);

        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setId(1);
        typeDTO.setName("fuoco");
        typeDTO.setIcon("fs");

        pokemonToTradeDTO.setType(typeDTO);

        MoveDTO moveDTO = new MoveDTO();
        moveDTO.setId(1);
        moveDTO.setName("fuocobomba");
        moveDTO.setType(typeDTO);
        moveDTO.setPower(90);
        List<MoveDTO> moveDTOS = new ArrayList<>();
        moveDTOS.add(moveDTO);

        pokemonToTradeDTO.setMoves(moveDTOS);
        pokemonToTradeDTO.setTrainerName("geppetto");

        // Crea un mock del PokemonDTO da ricevere
        receivedPokemonDTO = new PokemonDTO();
        receivedPokemonDTO.setId(1);
        receivedPokemonDTO.setName("pikachu");
        receivedPokemonDTO.setSprite(null);
        receivedPokemonDTO.setCurrentHp(80);
        receivedPokemonDTO.setMaxHp(120);

        TypeDTO receivedTypeDTO = new TypeDTO();
        receivedTypeDTO.setId(1);
        receivedTypeDTO.setName("elettricità");
        receivedTypeDTO.setIcon("gfdgtr");
        receivedPokemonDTO.setMoves(moveDTOS);

        receivedPokemonDTO.setType(receivedTypeDTO);
        receivedPokemonDTO.setTrainerName("pippo");
    }

    @Test
    void testInitiateTradeRequest() {
        // Simula la risposta di "Pokémon DAJE"
        ResponseEntity<PokemonDTO> response = new ResponseEntity<>(receivedPokemonDTO, HttpStatus.OK);

        // Configura il mock di RestTemplate per restituire la risposta simulata
        when(restTemplate.postForEntity(any(String.class), any(PokemonDTO.class), any(Class.class)))
                .thenReturn(response);

        // Chiama il metodo da testare
        tradeController.gigio(pokemonToTradeDTO);

        // Verifica che il database sia stato aggiornato correttamente
        PokemonEntity pokemonEntityTraded = mapper.map(pokemonToTradeDTO, PokemonEntity.class);
        verify(pokemonRepository).delete(pokemonEntityTraded);

        PokemonEntity pokemonEntityReceived = mapper.map(receivedPokemonDTO, PokemonEntity.class);
        verify(pokemonRepository).save(pokemonEntityReceived);
    }
}