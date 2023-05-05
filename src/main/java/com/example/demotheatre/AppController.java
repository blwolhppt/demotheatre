package com.example.demotheatre;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    private TheatreService service;

    // основная страничка
    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword){
        List<Theatre> listTheatre = service.listAll(keyword);
        model.addAttribute("listTheatre", listTheatre);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    // для добавления информации о пьесе
    @RequestMapping("/new")
    public String showNewTheatreForm(Model model){
        Theatre theatre = new Theatre();
        model.addAttribute("theatre", theatre);
        return "new_theatre";
    }

    // для сохранения информации о пьесе
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTheatre(@ModelAttribute("theatre") Theatre theatre){
        service.save(theatre);
        return "redirect:/";
    }

    // для редактирования (по id)
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditTheatreFrom(@PathVariable(name="id") Long id){
        ModelAndView mav = new ModelAndView("edit_theatre");
        Theatre theatre = service.get(id);
        mav.addObject("theatre", theatre);
        return mav;
    }

    // для удаления (по id)
    @RequestMapping("/delete/{id}")
    public String deleteTheatre(@PathVariable(name="id") Long id){
        service.delete(id);
        return "redirect:/";
    }

}
