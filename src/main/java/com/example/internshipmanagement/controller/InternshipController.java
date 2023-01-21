package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.InternshipDto;
import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.enums.ERole;
import com.example.internshipmanagement.model.Internship;
import com.example.internshipmanagement.model.Role;
import com.example.internshipmanagement.service.InternshipService;
import com.example.internshipmanagement.service.RoleService;
import com.example.internshipmanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/home/internships")
public class InternshipController {
    private final InternshipService internshipService;
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InternshipController(InternshipService internshipService, RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.internshipService = internshipService;
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView getInternshipList(Model model,
                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                          @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                          @ModelAttribute(value = "searchForm") SearchForm searchForm) {
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        Pageable pageable = null;
        Page<Internship> internshipPage = internshipService.getAll(pageable, "username", searchForm.getKeyword(), page, size);
        int totalPages = internshipPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("internshipList", internshipPage);
        return new ModelAndView("internship/internship-list");
    }

    @GetMapping("/save-form")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView saveInternshipForm(Model model, InternshipDto internshipDto) {
        model.addAttribute("internship", internshipDto);
        return new ModelAndView("internship/new-internship");
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveInternship(@ModelAttribute("internship") InternshipDto internshipDto) {
        Set<Role> roles = new HashSet<>();
        Role role = roleService.getRoleByName(ERole.ROLE_INTERNSHIP)
                .orElseThrow(() -> new RuntimeException("Role is not found!"));
        roles.add(role);
        internshipDto.setRoles(roles);
        String password = passwordEncoder.encode(internshipDto.getPassword());
        internshipDto.setPassword(password);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        internshipDto.setCreate_id(Math.toIntExact(userDto.getId()));
        internshipDto.setModified_id(Math.toIntExact(userDto.getId()));
        internshipService.save(internshipDto);
        return new RedirectView("/home/internships/");
    }

    @GetMapping("/update-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateInternshipFrom(Model model, @PathVariable Long id) {
        InternshipDto internshipDto = internshipService.getInternshipById(id);
        model.addAttribute("internship", internshipDto);
        return new ModelAndView("internship/update-internship");
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView updateInternship(@PathVariable Long id, @Valid InternshipDto internshipDto,
                                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            internshipDto.setId(id);
            return new RedirectView("/update-form/" + id);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        internshipDto.setModified_id(Math.toIntExact(userDto.getId()));
        internshipService.update(id, internshipDto);
        return new RedirectView("/home/internships/");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteInternship(@PathVariable Long id) {
        internshipService.delete(id);
        return new RedirectView("/home/internships/");
    }
}
