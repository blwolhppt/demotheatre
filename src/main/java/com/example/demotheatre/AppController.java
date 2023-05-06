package com.example.demotheatre;


import java.util.List;

import com.example.demotheatre.blog.BlogService;
import com.example.demotheatre.config.UserInfo;
import com.example.demotheatre.config.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AppController {


    private final TheatreService theatreService;
    private final BlogService blogService;

    @Autowired
    private UserService userService;



    @PostMapping("/auth/register")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        System.out.println("UserInfo object is: " + userInfo);
        userService.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }
    @GetMapping("/auth/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "register";
    }




    // основная страничка
    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword){
        List<Theatre> listTheatre = theatreService.listAll(keyword);
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
        theatreService.save(theatre);
        return "redirect:/";
    }

    // для редактирования (по id)
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditTheatreFrom(@PathVariable(name="id") Long id){
        ModelAndView mav = new ModelAndView("edit_theatre");
        Theatre theatre = theatreService.get(id);
        mav.addObject("theatre", theatre);
        return mav;
    }

    // для удаления (по id)
    @RequestMapping("/delete/{id}")
    public String deleteTheatre(@PathVariable(name="id") Long id){
        theatreService.delete(id);
        return "redirect:/";
    }




    //  регистрация, вход
    @GetMapping("/login_page")
    public String showLogin() {
        return "login_page";
    }
    @GetMapping("/about_us")
    public String showAbout() {
        return "about_us";
    }
    @PostMapping("/login_page")
    public String SuccessLogin(@RequestParam String username, HttpSession session) {
        System.out.println("Username is: " + username);
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute("username", currentUser);
        return "redirect:/";
    }




}
