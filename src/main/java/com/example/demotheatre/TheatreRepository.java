package com.example.demotheatre;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    @Query("SELECT p FROM Theatre p WHERE CONCAT(p.name_play, ' ', p.director, ' ', p.data, ' ', p.duration, ' ', p.adress) LIKE %?1%")
    List<Theatre> search(String keyword);
}
