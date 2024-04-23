package it.alten.pokemonao.database.repository;

import it.alten.pokemonao.database.entity.TypeEntity;
import it.alten.pokemonao.dtos.TypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity,Integer> {
    TypeEntity findByName(String name);
}
