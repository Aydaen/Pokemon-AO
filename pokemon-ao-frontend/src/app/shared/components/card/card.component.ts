import {Component, Input} from '@angular/core';
import {NgClass, NgForOf} from "@angular/common";
import {MoveModel} from "../../models/move.model";

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [
    NgClass,
    NgForOf
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {
@Input() name!:string;
@Input() moves!:MoveModel[];
@Input() typeIcon!:string;
@Input() sprite!:string;
@Input() type!:string;
}
