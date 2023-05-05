package com.example.demotheatre.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository repo;
    public List<Blog> listAll(String keyword) {
        if (keyword!=null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    public void save(Blog blog) {
        repo.save(blog);
    }
    public Blog get(Long id) {
        return repo.findById(id).get();
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
