package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.ReviewsDto;
import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.enums.ERole;
import com.example.internshipmanagement.model.Reviews;
import com.example.internshipmanagement.model.Role;
import com.example.internshipmanagement.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping({"/home-mentor/reviews", "/home-internship/reviews"})
public class ReviewController {

    private final ReviewService service;
    private final UserService userService;
    private final MentorService mentorService;
    private final InternshipService internshipService;
    private final RoleService roleService;

    public ReviewController(ReviewService service, UserService userService, MentorService mentorService, InternshipService internshipService, RoleService roleService) {
        this.service = service;
        this.userService = userService;
        this.mentorService = mentorService;
        this.internshipService = internshipService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('MENTOR') or hasRole('INTERNSHIP')")
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
        Page<Reviews> reviewsPage = service.getAll(pageable, searchForm.getKeyword(), page, size, sort, field);
        int totalPages = reviewsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("reviewList", reviewsPage);
        return new ModelAndView("review/review-list");
    }

    @GetMapping("/save-form")
    @PreAuthorize("hasRole('MENTOR') or hasRole('INTERNSHIP')")
    public ModelAndView saveMentorForm(Model model, ReviewsDto reviewsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        reviewsDto.setReviewer(userDto.getUsername());
        model.addAttribute("review", reviewsDto);
        List<String> nameList;
        if (!mentorService.existedByUsername(userDto.getUsername()))
            nameList = mentorService.getAllMentor()
                    .stream().map(UserDto::getUsername)
                    .collect(Collectors.toList());
        else {
            nameList = internshipService.getAll()
                    .stream().map(UserDto::getUsername)
                    .collect(Collectors.toList());
        }
        model.addAttribute("nameList", nameList);
        return new ModelAndView("review/new-review");
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('MENTOR') or hasRole('INTERNSHIP')")
    public RedirectView saveMentor(@ModelAttribute("review") ReviewsDto reviewsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        reviewsDto.setReviewer(userDto.getUsername());
        reviewsDto.setCreateId(Math.toIntExact(userDto.getId()));
        reviewsDto.setModifiedId(Math.toIntExact(userDto.getId()));
        service.save(reviewsDto);

        Role roleMentor = roleService.getRoleByName(ERole.ROLE_MENTOR)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        if (userDto.getRoles().contains(roleMentor))
            return new RedirectView("/home-mentor/reviews/");
        else
            return new RedirectView("/home-internship/reviews/");
    }

    @GetMapping("/update-form/{id}")
    @PreAuthorize("hasRole('MENTOR') or hasRole('INTERNSHIP')")
    public ModelAndView updateMentorFrom(Model model, @PathVariable Long id) {
        ReviewsDto reviewsDto = service.getReviewById(id);
        model.addAttribute("review", reviewsDto);
        List<String> nameList;
        if (!mentorService.existedByUsername(reviewsDto.getReviewer()))
            nameList = mentorService.getAllMentor().stream()
                    .map(UserDto::getUsername)
                    .filter(s -> !s.equals(reviewsDto.getObject()))
                    .collect(Collectors.toList());
        else {
            nameList = internshipService.getAll()
                    .stream().map(UserDto::getUsername)
                    .filter(s -> !s.equals(reviewsDto.getObject()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("nameList", nameList);
        return new ModelAndView("review/update-review");
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('MENTOR') or hasRole('INTERNSHIP')")
    public RedirectView updateMentor(@PathVariable Long id, @Valid ReviewsDto reviewsDto,
                                     BindingResult result, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());

        if (result.hasErrors()) {
            reviewsDto.setId(id);

            Role roleMentor = roleService.getRoleByName(ERole.ROLE_MENTOR)
                    .orElseThrow(() -> new RuntimeException("Role is not found"));
            if (userDto.getRoles().contains(roleMentor))
                return new RedirectView("/home-mentor/reviews/update-form/" + id);
            else
                return new RedirectView("/home-internship/reviews/update-form/" + id);
        }
        reviewsDto.setModifiedId(Math.toIntExact(userDto.getId()));
        service.update(id, reviewsDto);

        Role roleMentor = roleService.getRoleByName(ERole.ROLE_MENTOR)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        if (userDto.getRoles().contains(roleMentor))
            return new RedirectView("/home-mentor/reviews/");
        else
            return new RedirectView("/home-internship/reviews/");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MENTOR') or hasRole('INTERNSHIP')")
    public RedirectView deleteMentor(@PathVariable Long id) {
        service.delete(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());

        Role roleMentor = roleService.getRoleByName(ERole.ROLE_MENTOR)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        if (userDto.getRoles().contains(roleMentor))
            return new RedirectView("/home-mentor/reviews/");
        else
            return new RedirectView("/home-internship/reviews/");
    }
}
