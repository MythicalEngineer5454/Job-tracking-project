This project aims to build a smart, AI-powered assistant to help users efficiently search for jobs, generate tailored resumes and cover letters, and track their job applications—all in one place.

1. Job Discovery by Keywords

   -Users can search for jobs using specific keywords. The current version supports:

      -Side jobs

      -Computer science roles

  -Jobs are fetched using web scraping tools or public APIs from platforms like Indeed and LinkedIn. The system extracts and stores information such as:

     Job title

      Company

      Location

      Description

      Application link

      Date posted


2. AI-Powered Resume and Cover Letter Generation

    -The software leverages OpenAI’s ChatGPT API to generate customized application documents:
    Based on a user-uploaded master resume
   
    -Tailored to each job’s requirements

    -Output includes both a personalized resume and cover letter

    -Supports tone customization (formal, casual, confident)

    -Users can download, preview, and edit documents before use


3. Master Resume Management
    -Users can upload or edit a central “master resume.”

    -The system: Parses and stores resume content in structured form

    -Supports multiple formats (PDF, DOCX, TXT)

    -Allows editing and reusing across different job applications


4. Job Application Tracker

   -The platform helps users track every application they submit:

   -Records job title, company, date applied, status, and documents used

   -Application statuses include: Applied, Interviewing, Offer, Rejected, Ghosted

   -Users can filter, update, and view their job application history


Technology Stack
   Layer   Tech Used Purpose

Frontend: 
  Angular; 
  User interface, forms, tracking_dashboard
  
Backend: 
  Spring_Boot; 
  REST_APIs, logic_coordination
  
Database: 
  PostgreSQL; 
  Data persistence for jobs, resumes, logs

AI_Integration: 
  Gemini; 
  Smart document generation and job filtering 

Scraping
  Python
  Job fetching from online listings



































