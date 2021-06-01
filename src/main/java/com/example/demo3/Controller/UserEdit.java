package com.example.demo3.Controller;

import com.example.demo3.Domain.User;
import com.example.demo3.Repository.UserRepository;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserEdit
{
    private UserRepository userRepository;
    private User user;

    @Autowired
    public void setUser(User user)
    {
        this.user = user;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GetMapping("/edit")
    public String editPage(HttpSession session, Model model)
    {
        if (userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).isPresent())
        {
            user = userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).get();
            model.addAttribute("user",user);

            return "edit";
        }
        return "redirect:/index";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute User user,
                             HttpSession session,
                             Model model)
    {
        if (session.getAttribute("userEmail").equals(user.getEmail()))
        {
            userRepository.save(user);
        } else
        {
            if (userRepository.findById(user.getEmail()).isPresent())
            {
                model.addAttribute("emailStatus","duplicate");
                return "edit";
            }
            else
            {
                userRepository.deleteById(String.valueOf(session.getAttribute("userEmail")));
                userRepository.save(user);
                session.setAttribute("userEmail",user.getEmail());
            }
        }
        return "redirect:/edit";
    }
}
