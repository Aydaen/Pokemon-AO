package it.alten.pokemonao.database.repository;

import it.alten.pokemonao.database.entity.MoveEntity;
import it.alten.pokemonao.dtos.MoveDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoveRepository extends JpaRepository<MoveEntity,Integer> {

}
