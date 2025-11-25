// src/app/app.routes.ts
import { Routes } from '@angular/router';

// Import components (you'll create some of these later)
import { JobList } from './job-list/job-list';
import { JobDetailComponent } from './job-detail/job-detail.component';
import { ApplicationListComponent } from './applications/application-list.component';
import { ApplicationDetailComponent } from './applications/application-detail.component';
import { ResumeUploadComponent } from './resume/resume-upload.component';
import { ResumeMasterComponent } from './resume/resume-master.component';
import { AiMatchComponent } from './ai/ai-match.component';
import { AiGenerateComponent } from './ai/ai-generate.component';
import { SettingsComponent } from './settings/settings.component';
import { DebugComponent } from './debug/debug.component';

export const routes: Routes = [
  // Redirect base URL "/" → "/jobs"
  { path: '', redirectTo: 'jobs', pathMatch: 'full' },

  // Jobs
  { path: 'jobs', component: JobList },
  { path: 'jobs/:id', component: JobDetailComponent },

  // Applications
  { path: 'applications', component: ApplicationListComponent },
  { path: 'applications/:id', component: ApplicationDetailComponent },

  // Resume
  { path: 'resume/upload', component: ResumeUploadComponent },
  { path: 'resume/master', component: ResumeMasterComponent },

  // AI tools
  { path: 'ai/match', component: AiMatchComponent },
  { path: 'ai/generate/:jobId', component: AiGenerateComponent },

  // Settings & Debug
  { path: 'settings', component: SettingsComponent },
  { path: 'debug', component: DebugComponent },

  // Fallback: unknown route → jobs
  { path: '**', redirectTo: 'jobs' }
];




