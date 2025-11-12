package com.gallery.model;


import jakarta.persistence.*;


@Entity
public class Artwork {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private String title;
private String artist;
private String category;


@Column(length = 2000)
private String description;


// URL to the uploaded file (served from /uploads)
private String imageUrl;


private int likes = 0;


// getters and setters
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
public String getArtist() { return artist; }
public void setArtist(String artist) { this.artist = artist; }
public String getCategory() { return category; }
public void setCategory(String category) { this.category = category; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public String getImageUrl() { return imageUrl; }
public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
public int getLikes() { return likes; }
public void setLikes(int likes) { this.likes = likes; }
}
