package com.gallery.service;


import com.gallery.model.Artwork;
import com.gallery.repository.ArtworkRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ArtworkService {
private final ArtworkRepository repo;
public ArtworkService(ArtworkRepository repo) { this.repo = repo; }


public List<Artwork> all() { return repo.findAll(); }
public Optional<Artwork> get(Long id) { return repo.findById(id); }
public Artwork save(Artwork a) { return repo.save(a); }
public void delete(Long id) { repo.deleteById(id); }
public List<Artwork> findByCategory(String cat) { return repo.findByCategoryContainingIgnoreCase(cat); }
}
