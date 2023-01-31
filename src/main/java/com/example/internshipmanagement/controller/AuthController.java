package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.dto.request.LoginRequest;
import com.example.internshipmanagement.enums.ERole;
import com.example.internshipmanagement.model.Role;
import com.example.internshipmanagement.service.RoleService;
import com.example.internshipmanagement.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, RoleService roleService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    public ModelAndView login(Model model) {
        LoginRequest loginRequest = new LoginRequest();
        model.addAttribute("login", loginRequest);
        return new ModelAndView("login");
    }

    @PostMapping("/signin")
    public RedirectView login(@Validated @ModelAttribute("user") LoginRequest loginRequest, Model model) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        Role roleAdmin = roleService.getRoleByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        Role roleMentor = roleService.getRoleByName(ERole.ROLE_MENTOR)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        if (userDto.getRoles().contains(roleAdmin))
            return new RedirectView("/home-admin");
        else if (userDto.getRoles().contains(roleMentor)){
            return new RedirectView("/home-mentor");
        } else {
            return new RedirectView("/home-internship");
        }
    }

    @GetMapping("/home-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView adminHomePage() {
        return new ModelAndView("admin/home");
    }

    @GetMapping("/home-mentor")
    @PreAuthorize("hasRole('MENTOR')")
    public ModelAndView mentorHomePage() {
        return new ModelAndView("mentor/home");
    }

    @GetMapping("/home-internship")
    @PreAuthorize("hasRole('INTERNSHIP')")
    public ModelAndView internshipHomePage() {
        return new ModelAndView("internship/home");
    }
}
