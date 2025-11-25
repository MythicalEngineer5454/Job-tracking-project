import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],   // no RouterOutlet needed
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  // you can put properties here later if you want
}
