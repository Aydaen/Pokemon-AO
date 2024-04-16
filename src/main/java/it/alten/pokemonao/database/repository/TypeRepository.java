package it.alten.pokemonao.database.repository;

import it.alten.pokemonao.database.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity,Integer> {
}
