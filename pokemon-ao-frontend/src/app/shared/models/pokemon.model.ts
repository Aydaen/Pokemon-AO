import {TypeModel} from "./type.model";
import {MoveModel} from "./move.model";

export interface PokemonModel {
  pokemonPokeApiId: number;
  nickname: string;
  sprite: string;
  currentHp: number;
  maxHp: number;
  type: TypeModel;
  moves: MoveModel[];
}
