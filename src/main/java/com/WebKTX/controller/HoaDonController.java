package com.WebKTX.controller;

import com.WebKTX.model.Hoadon;
import com.WebKTX.model.User;
import com.WebKTX.repository.UserRepository;
import com.WebKTX.service.UserDetail;
import com.WebKTX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Controller
public class HoaDonController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private UserService userService;

    @GetMapping("/hoadon")
    public String hoadon(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetail currentUser = (UserDetail) auth.getPrincipal();
        Integer userId = currentUser.id();
        User user = repo.findById(userId).orElse(null);
        if(user != null)
        {
            Set<Hoadon> listHoaDon = user.getIdPhong().getHoadons();
            model.addAttribute("listHoadon",listHoaDon);
        }

        return "hoadon";

    }
}