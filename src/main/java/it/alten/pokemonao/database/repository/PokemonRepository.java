package it.alten.pokemonao.database.repository;

import it.alten.pokemonao.database.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity,Integer> {
}
