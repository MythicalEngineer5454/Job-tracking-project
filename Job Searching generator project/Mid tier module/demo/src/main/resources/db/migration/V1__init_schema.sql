-- USERS (optional now, enables multi-user later)
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password_hash VARCHAR(255),
  created_at TIMESTAMP DEFAULT NOW()
);

-- JOB LISTINGS
CREATE TABLE jobs (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  company VARCHAR(255),
  location VARCHAR(255),
  description TEXT,
  link TEXT,
  category VARCHAR(100),
  date_posted TIMESTAMP DEFAULT NOW(),
  scraped BOOLEAN DEFAULT FALSE
);

-- MASTER RESUME
CREATE TABLE resumes (
  id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users(id) ON DELETE CASCADE,
  name VARCHAR(255),
  content TEXT,
  uploaded_at TIMESTAMP DEFAULT NOW()
);

-- AI-GENERATED DOCS
CREATE TABLE ai_documents (
  id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users(id) ON DELETE CASCADE,
  job_id INT REFERENCES jobs(id) ON DELETE CASCADE,
  resume_text TEXT,
  cover_letter_text TEXT,
  tone VARCHAR(50),
  created_at TIMESTAMP DEFAULT NOW()
);

-- APPLICATION TRACKER
CREATE TABLE applications (
  id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users(id) ON DELETE CASCADE,
  job_id INT REFERENCES jobs(id) ON DELETE CASCADE,
  ai_doc_id INT REFERENCES ai_documents(id) ON DELETE SET NULL,
  status VARCHAR(50) DEFAULT 'Applied',
  applied_date TIMESTAMP DEFAULT NOW(),
  notes TEXT
);

-- TAGS
CREATE TABLE job_tags (
  id SERIAL PRIMARY KEY,
  job_id INT REFERENCES jobs(id) ON DELETE CASCADE,
  tag VARCHAR(100)
);

-- Helpful indexes
CREATE INDEX idx_jobs_company ON jobs(company);
CREATE INDEX idx_jobs_category ON jobs(category);
CREATE INDEX idx_applications_status ON applications(status);
CREATE INDEX idx_job_tags_job_id ON job_tags(job_id);
