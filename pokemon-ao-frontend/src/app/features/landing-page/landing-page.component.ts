import {Component, OnInit} from '@angular/core';
import {ButtonComponent} from "../../shared/components/button/button.component";
import {LowerCasePipe, NgForOf, TitleCasePipe} from "@angular/common";
import {CardComponent} from "../../shared/components/card/card.component";
import {PokemonService} from "../../core/services/pokemon.service";
import {PokemonModel} from "../../shared/models/pokemon.model";

class Pokemon {
  name!:string;
  moves!:string[];
  type!: string;
  sprite!:string;
}

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
    this.pokemonService.exchangePokemon().subscribe(data => {
      const shuffledData = this.shuffleArray(data).slice(0, 6);
      shuffledData.forEach(value => {
        if (!this.pokemon.find(p => p.nickname === value.name)) {
          this.pokemon.push(value);
        }
      });
      console.log(this.pokemon);
    });
  }

  shuffleArray(array: any[]): any[] {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

  pokemon: PokemonModel[] = []

  getTypeIconPath(type: string): string {
    return `${type}`;
  }
}
