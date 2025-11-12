package com.gallery.controller;
import java.util.List;


@RestController
@RequestMapping("/api/artworks")
@CrossOrigin(origins = "*")
public class ArtworkController {
private final ArtworkService service;
private final Path uploadDir = Paths.get("src/main/resources/static/uploads");


public ArtworkController(ArtworkService service) throws IOException {
this.service = service;
Files.createDirectories(uploadDir);
}


@GetMapping
public List<Artwork> all() {
return service.all();
}


@GetMapping("/{id}")
public ResponseEntity<Artwork> get(@PathVariable Long id) {
return service.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}


@PostMapping(consumes = {"multipart/form-data"})
public Artwork create(@RequestPart("metadata") Artwork artwork,
@RequestPart(value = "image", required = false) MultipartFile file) throws IOException {
if (file != null && !file.isEmpty()) {
String fname = System.currentTimeMillis() + "_" + file.getOriginalFilename().replaceAll("\\s+", "_");
Path dest = uploadDir.resolve(fname);
file.transferTo(dest.toFile());
artwork.setImageUrl("/uploads/" + fname);
}
return service.save(artwork);
}


@PutMapping("/{id}")
public ResponseEntity<Artwork> update(@PathVariable Long id, @RequestBody Artwork payload) {
return service.get(id).map(existing -> {
existing.setTitle(payload.getTitle());
existing.setArtist(payload.getArtist());
existing.setCategory(payload.getCategory());
existing.setDescription(payload.getDescription());
existing.setImageUrl(payload.getImageUrl());
existing.setLikes(payload.getLikes());
service.save(existing);
return ResponseEntity.ok(existing);
}).orElse(ResponseEntity.notFound().build());
}


@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
service.delete(id);
return ResponseEntity.ok().build();
}


@PostMapping("/{id}/like")
public ResponseEntity<Artwork> like(@PathVariable Long id) {
return service.get(id).map(a -> {
a.setLikes(a.getLikes() + 1);
service.save(a);
return ResponseEntity.ok(a);
}).orElse(ResponseEntity.notFound().build());
}
}
