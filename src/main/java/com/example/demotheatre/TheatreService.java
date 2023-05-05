package com.example.demotheatre;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository rep;

    public List<Theatre> listAll(String keyword) {
        if (keyword != null) {
            return rep.search(keyword);
        }
        return rep.findAll();
    }

    // метод для сохранение
    public void save(Theatre theatre) {
        rep.save(theatre);
    }

    // метод для удаления
    public void delete(Long id) {
        rep.deleteById(id);
    }

    // метод для получения, т.е. поиска
    public Theatre get(Long id) {
        return rep.findById(id).get();
    }



}
