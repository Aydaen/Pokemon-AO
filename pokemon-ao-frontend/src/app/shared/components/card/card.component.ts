import {Component, Input} from '@angular/core';
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [
    NgClass
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {
@Input() name!:string;
@Input() moves!:string[];
@Input() typeIcon!:string;
@Input() sprite!:string;
@Input() type!:string;
}
