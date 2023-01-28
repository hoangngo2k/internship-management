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
import org.springframework.security.crypto.password.PasswordEncoder;
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
        if (userDto.getRoles().contains(roleAdmin))
            return new RedirectView("/home");
        else {
            return new RedirectView("/reviews/");
        }
    }

    @GetMapping("/home")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }
}
