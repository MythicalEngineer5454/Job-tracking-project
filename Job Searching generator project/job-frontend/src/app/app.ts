import { Component } from '@angular/core';
import { JobList } from './job-list/job-list';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [JobList],   // no RouterOutlet needed
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  // you can put properties here later if you want
}
