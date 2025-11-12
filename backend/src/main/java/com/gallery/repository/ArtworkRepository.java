package com.gallery.repository;


import com.gallery.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
List<Artwork> findByCategoryContainingIgnoreCase(String category);
}
