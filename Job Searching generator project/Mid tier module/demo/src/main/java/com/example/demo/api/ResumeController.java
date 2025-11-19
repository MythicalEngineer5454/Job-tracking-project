// api/ResumeController.java
package com.example.demo.api;
import com.example.demo.model.Resume;
import com.example.demo.repo.ResumeRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/resumes") @CrossOrigin(origins="*")
public class ResumeController {
  private final ResumeRepo repo;
  public ResumeController(ResumeRepo repo){ this.repo = repo; }

  @GetMapping public List<Resume> all(){ return repo.findAll(); }

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public Resume create(@RequestBody Resume r){ r.setId(null); return repo.save(r); }
}
