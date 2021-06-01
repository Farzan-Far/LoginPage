package com.example.demo3.Controller;

import com.example.demo3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserLogin
{

    private UserRepository userRepository ;

    @Autowired
    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @PostMapping("/login")
    public String submitLogin(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              Model model)
    {
        if (userRepository.findById(email).isPresent())
        {
            if (userRepository.findById(email).get().getPassword().equals(password))
            {
                session.setAttribute("userEmail",email);
                return "redirect:/index";
            }
            else
            {
                model.addAttribute("password","false");
            }
        }
        else
        {
            model.addAttribute("email","NotFound");
        }
        return "login";
    }
}
