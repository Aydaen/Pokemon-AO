import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {PokemonModel} from "../../shared/models/pokemon.model";

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  constructor(private http: HttpClient) { }

  exchangePokemon() {
    return this.http.get<PokemonModel[]>('http://localhost:8080/pokemon');
  }
}
