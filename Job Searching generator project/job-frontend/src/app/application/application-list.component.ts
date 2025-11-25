import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Application {
  id: number;
  jobTitle: string;
  company: string;
  status: string;
  appliedDate: string;
}

@Component({
  selector: 'app-application-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>My Applications</h2>

    <div *ngFor="let app of applications" 
         style="border:1px solid #ccc; padding:10px; margin:10px; border-radius:8px;">
      <h3>{{ app.jobTitle }}</h3>
      <p><strong>Company:</strong> {{ app.company }}</p>
      <p><strong>Status:</strong> {{ app.status }}</p>
      <p><strong>Applied:</strong> {{ app.appliedDate }}</p>
    </div>
  `
})
export class ApplicationListComponent {

  // Hardcoded test data
  applications: Application[] = [
    {
      id: 1,
      jobTitle: 'Frontend Developer Intern',
      company: 'Google',
      status: 'Applied',
      appliedDate: '2025-01-22'
    },
    {
      id: 2,
      jobTitle: 'Python Developer',
      company: 'Amazon',
      status: 'Interview',
      appliedDate: '2025-02-01'
    },
    {
      id: 3,
      jobTitle: 'Junior Backend Developer',
      company: 'Shopify',
      status: 'Rejected',
      appliedDate: '2025-02-12'
    }
  ];
}
