import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RealticketComponent } from "./realticket/realticket.component";
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,RouterOutlet, RealticketComponent,],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'realtimeticketing';
}
