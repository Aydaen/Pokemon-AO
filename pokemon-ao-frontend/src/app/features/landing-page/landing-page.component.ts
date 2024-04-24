import {Component, OnInit} from '@angular/core';
import {ButtonComponent} from "../../shared/components/button/button.component";
import {LowerCasePipe, NgForOf, TitleCasePipe} from "@angular/common";
import {CardComponent} from "../../shared/components/card/card.component";
import {PokemonService} from "../../core/services/pokemon.service";
import {PokemonModel} from "../../shared/models/pokemon.model";

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [
    ButtonComponent,
    NgForOf,
    CardComponent,
    LowerCasePipe,
    TitleCasePipe
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent implements OnInit {

  ngOnInit(): void {
    this.fetchPokemon();
  }

  constructor(private pokemonService: PokemonService) {
  }

  fetchPokemon() {
    this.pokemonService.getAllPokemon().subscribe(data => {
      const firstSixPokemon = data.slice(0, 6);
      firstSixPokemon.forEach(value => {
        this.pokemon.push(value);
      });
    });
  }


  pokemon: PokemonModel[] = []
}
