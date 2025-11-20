// src/app/job-list/job-list.ts
// src/app/job-list/job-list.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobService, Job } from '../services/job.service';  // 👈 FIXED

@Component({
  selector: 'app-job-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './job-list.html',
  styleUrls: ['./job-list.css']
})
export class JobList implements OnInit {
  jobs: Job[] = [];

  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    this.jobService.getJobs().subscribe({
      next: (data) => {
        this.jobs = data;
        console.log('Jobs loaded:', data);
      },
      error: (err) => console.error('Error fetching jobs:', err)
    });
  }
}



