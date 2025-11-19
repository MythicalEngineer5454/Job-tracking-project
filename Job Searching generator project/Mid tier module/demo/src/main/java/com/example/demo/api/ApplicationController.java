// api/ApplicationController.java
package com.example.demo.api;
import com.example.demo.model.Application;
import com.example.demo.repo.ApplicationRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestController @RequestMapping("/api/applications") @CrossOrigin(origins="*")
public class ApplicationController {
  private final ApplicationRepo repo;
  public ApplicationController(ApplicationRepo repo){ this.repo = repo; }

  @GetMapping public List<Application> all(){ return repo.findAll(); }

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public Application create(@RequestBody Application a){
    a.setId(null);
    if (a.getStatus()==null) a.setStatus("Applied");
    return repo.save(a);
  }
}

