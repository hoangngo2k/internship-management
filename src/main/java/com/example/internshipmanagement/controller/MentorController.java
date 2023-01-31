package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.MentorDto;
import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.enums.ERole;
import com.example.internshipmanagement.model.Mentor;
import com.example.internshipmanagement.model.Role;
import com.example.internshipmanagement.service.MentorService;
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
@RequestMapping("/home-admin/mentors")
public class MentorController {
    private final MentorService mentorService;
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public MentorController(MentorService mentorService, RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.mentorService = mentorService;
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView getAllMentor(Model model,
                                     @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                     @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort,
                                     @RequestParam(name = "field", required = false, defaultValue = "id") String field,
                                     @ModelAttribute(value = "searchForm") SearchForm searchForm) {
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        String sortDirection = sort.equals("asc") ? "desc" : "asc";
        model.addAttribute("sortDirection", sortDirection);
        Pageable pageable = null;
        Page<Mentor> mentorPage = mentorService.getAll(pageable, searchForm.getKeyword(), page, size, sort, field);
        int totalPages = mentorPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("mentorList", mentorPage);
        return new ModelAndView("mentor/mentor-list");
    }

    @GetMapping("/save-form")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView saveMentorForm(Model model, MentorDto mentorDto) {
        model.addAttribute("mentor", mentorDto);
        return new ModelAndView("mentor/new-mentor");
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveMentor(@ModelAttribute("mentor") MentorDto mentorDto) {
        Set<Role> roles = new HashSet<>();
        Role role = roleService.getRoleByName(ERole.ROLE_MENTOR)
                .orElseThrow(() -> new RuntimeException("Role is not found!"));
        roles.add(role);
        mentorDto.setRoles(roles);

        String password = passwordEncoder.encode(mentorDto.getPassword());
        mentorDto.setPassword(password);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());

        mentorDto.setCreateId(Math.toIntExact(userDto.getId()));
        mentorDto.setModifiedId(Math.toIntExact(userDto.getId()));

        mentorService.save(mentorDto);
        return new RedirectView("/home-admin/mentors/");
    }

    @GetMapping("/update-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateMentorFrom(Model model, @PathVariable Long id) {
        MentorDto mentorDto = mentorService.getMentorById(id);
        model.addAttribute("mentor", mentorDto);
        return new ModelAndView("mentor/update-mentor");
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView updateMentor(@PathVariable Long id, @Valid MentorDto mentorDto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            mentorDto.setId(id);
            return new RedirectView("/home-admin/mentors/update-form/" + id);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());

        String password = passwordEncoder.encode(mentorDto.getPassword());
        mentorDto.setPassword(password);

        mentorDto.setModifiedId(Math.toIntExact(userDto.getId()));

        Set<Role> roles = new HashSet<>();
        Role role = roleService.getRoleByName(ERole.ROLE_MENTOR)
                .orElseThrow(() -> new RuntimeException("Role is not found!"));
        roles.add(role);
        mentorDto.setRoles(roles);

        mentorService.update(id, mentorDto);
        return new RedirectView("/home-admin/mentors/");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteMentor(@PathVariable Long id) {
        mentorService.delete(id);
        return new RedirectView("/home-admin/mentors/");
    }
}
