import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

@Component({
  selector: 'app-job-detail',
  standalone: true,
  imports: [RouterModule],
  template: `
    <h2>Job Detail Page</h2>
    <p>Job ID: {{ jobId }}</p>

    <button (click)="goBack()">⬅ Back to Jobs</button>
  `
})
export class JobDetailComponent {
  jobId: string | null = null;

  constructor(private route: ActivatedRoute, private router: RouterModule) {
    this.jobId = this.route.snapshot.paramMap.get('id');
  }

  goBack() {
    history.back();
  }
}
