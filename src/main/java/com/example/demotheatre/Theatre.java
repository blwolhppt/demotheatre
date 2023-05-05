package com.example.demotheatre;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@Table(name = "theatre")
public class Theatre {

    @Column(name = "id")
    private Long id; // ID

    @Column(name = "name_play")
    private String name_play; // название пьесы

    @Column(name = "director")
    private String director; // режиссер

    @Column(name = "data")
    private String data; // дата пьесы

    @Column(name = "duration")
    private String duration; // длительность пьесы

    @Column(name = "adress")
    private String adress; // адрес филиала театра

    protected Theatre() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // геттер и сеттер для ID
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // геттер и сеттер для name_play
    public String getName_play() { return name_play; }
    public void setName_play(String name_play) { this.name_play = name_play; }

    // геттер и сеттер для director
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    // геттер и сеттер для data
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    // геттер и сеттер для duration
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    // геттер и сеттер для adress
    public String getAdress() { return adress; }
    public void setAdress(String adress) { this.adress = adress; }

}
