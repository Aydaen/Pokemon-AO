package it.alten.PokemonAO.database.repository;

import it.alten.PokemonAO.database.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<Move,Integer> {
}
