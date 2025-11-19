package com.example.demo.Service;

import com.example.demo.model.Job;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JobServiceImpl implements JobService {

    private final Map<Long, Job> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public boolean addJob(Job job) {
        if (job == null || isBlank(job.getTitle())) return false;

        long id = idGen.getAndIncrement();
        // ensure id + sane defaults
        job.setId(id);
        if (job.getScraped() == null) job.setScraped(false);
        // let DB default handle datePosted later; for in-memory you can set it if you want:
        // if (job.getDatePosted() == null) job.setDatePosted(Instant.now());

        store.put(id, job);
        return true;
    }

    @Override
    public boolean updateJob(Long id, Job changes) {
        if (id == null || id <= 0 || changes == null) return false;
        Job existing = store.get(id);
        if (existing == null) return false;

        // PATCH-like updates: only apply provided fields
        if (!isBlank(changes.getTitle()))        existing.setTitle(changes.getTitle());
        if (!isBlank(changes.getCompany()))      existing.setCompany(changes.getCompany());
        if (!isBlank(changes.getLocation()))     existing.setLocation(changes.getLocation());
        if (!isBlank(changes.getDescription()))  existing.setDescription(changes.getDescription());
        if (!isBlank(changes.getLink()))         existing.setLink(changes.getLink());
        if (!isBlank(changes.getCategory()))     existing.setCategory(changes.getCategory());
        if (changes.getScraped() != null)        existing.setScraped(changes.getScraped());
        if (changes.getDatePosted() != null)     existing.setDatePosted(changes.getDatePosted());

        // put is optional since we mutated the object, but keeps semantics explicit
        store.put(id, existing);
        return true;
    }

    @Override
    public List<Job> retrieveValue() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean delete(Long id) {
        if (id == null || id <= 0) return false;
        return store.remove(id) != null;
    }

    private boolean isBlank(String s) { return s == null || s.isBlank(); }
}
