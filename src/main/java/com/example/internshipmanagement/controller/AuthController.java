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
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, RoleService roleService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
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
        if(userDto.getRoles().contains(roleAdmin))
            return new RedirectView("/home");
        else if (userDto.getRoles().contains(roleMentor)) {
            return new RedirectView("/reviews/");
        } else
            return new RedirectView("/");
    }

    @GetMapping("/home")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

//    @GetMapping("/register")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ModelAndView register(Model model) {
//        SignupRequest signupRequest = new SignupRequest();
//        model.addAttribute("signup", signupRequest);
//        return new ModelAndView("register");
//    }

//    @PostMapping("/signup")
//    @PreAuthorize("hasRole('ADMIN')")
//    public RedirectView register(@ModelAttribute("user") SignupRequest signupRequest) {
//        if (userService.existsByUsername(signupRequest.getUsername()) || userService.existsByEmail(signupRequest.getEmail()))
//            return new RedirectView("/register");
//        UserDto userDTO = new UserDto(signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()),
//                signupRequest.getEmail(), signupRequest.getFull_name(), signupRequest.getPhone_number(),
//                signupRequest.isIs_del_flg(), 1, 1);
//        Set<String> roleStr = signupRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//        if (roleStr == null) {
//            Role role = roleService.getRoleByName(ERole.ROLE_INTERNSHIP)
//                    .orElseThrow(() -> new RuntimeException("Role is not found"));
//            roles.add(role);
//        } else {
//            roleStr.forEach(s -> {
//                switch (s) {
//                    case "admin" -> {
//                        Role role = roleService.getRoleByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Role is not found"));
//                        roles.add(role);
//                    }
//                    case "mentor" -> {
//                        Role roleAdmin = roleService.getRoleByName(ERole.ROLE_MENTOR)
//                                .orElseThrow(() -> new RuntimeException("Role is not found"));
//                        roles.add(roleAdmin);
//                    }
//                    case "internship" -> {
//                        Role role = roleService.getRoleByName(ERole.ROLE_INTERNSHIP)
//                                .orElseThrow(() -> new RuntimeException("Role is not found"));
//                        roles.add(role);
//                    }
//                }
//            });
//        }
//        userDTO.setRoles(roles);
//        userService.save(userDTO);
//        return new RedirectView("/login");
//    }
}
