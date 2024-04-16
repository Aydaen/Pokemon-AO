package it.alten.pokemonao.database.repository;

import it.alten.pokemonao.database.entity.MoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<MoveEntity,Integer> {
}
