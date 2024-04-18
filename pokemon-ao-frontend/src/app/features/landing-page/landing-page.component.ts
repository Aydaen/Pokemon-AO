import { Component } from '@angular/core';
import {ButtonComponent} from "../../shared/components/button/button.component";
import {LowerCasePipe, NgForOf} from "@angular/common";
import {CardComponent} from "../../shared/components/card/card.component";

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
    LowerCasePipe
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {
  pokemon: Pokemon[] = [
    {
      name: 'Squirtle',
      moves: ['Tackle', 'Bubble', 'Water Gun', 'Withdraw'],
      type: 'Water',
      sprite: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/7.png'
    },
    {
      name: 'Dragonite',
      moves: ['Dragon Claw', 'Wing Attack', 'Thunder Punch', 'Fire Punch'],
      type: 'Dragon',
      sprite: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/149.png'
    },
    {
      name: 'Charizard',
      moves: ['Flamethrower', 'Fly', 'Dragon Rage', 'Slash'],
      type: 'Fire',
      sprite: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/6.png'
    },
    {
      name: 'Empoleon',
      moves: ['Hydro Pump', 'Ice Beam', 'Drill Peck', 'Flash Cannon'],
      type: 'Water',
      sprite: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/395.png'
    },
    {
      name: 'Ditto',
      moves: ['Transform'],
      type: 'Normal',
      sprite: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/132.png'
    },
    {
      name: 'Garchomp',
      moves: ['Dragon Claw', 'Earthquake', 'Fire Fang', 'Slash'],
      type: 'Dragon',
      sprite: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/445.png'
    }
  ];

  getTypeIconPath(type: string): string {
    return `assets/${type}Type.png`;
  }
}
