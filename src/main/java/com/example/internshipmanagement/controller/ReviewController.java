package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.ReviewsDto;
import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.model.Reviews;
import com.example.internshipmanagement.service.InternshipService;
import com.example.internshipmanagement.service.MentorService;
import com.example.internshipmanagement.service.ReviewService;
import com.example.internshipmanagement.service.UserService;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;
    private final UserService userService;
    private final MentorService mentorService;
    private final InternshipService internshipService;

    public ReviewController(ReviewService service, UserService userService, MentorService mentorService, InternshipService internshipService) {
        this.service = service;
        this.userService = userService;
        this.mentorService = mentorService;
        this.internshipService = internshipService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('MENTOR') or hasRole('INTERNSHIP')")
    public ModelAndView getAllMentor(Model model,
                                     @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                     @ModelAttribute(value = "searchForm") SearchForm searchForm) {
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        Pageable pageable = null;
        Page<Reviews> reviewsPage = service.getAll(pageable, "object", searchForm.getKeyword(), page, size);
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
            nameList = internshipService.getAllInternship()
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
        reviewsDto.setCreate_id(Math.toIntExact(userDto.getId()));
        reviewsDto.setModified_id(Math.toIntExact(userDto.getId()));
        service.save(reviewsDto);
        return new RedirectView("/reviews/");
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
            nameList = internshipService.getAllInternship()
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
        if (result.hasErrors()) {
            reviewsDto.setId(id);
            return new RedirectView("/reviews/update-form/" + id);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        reviewsDto.setModified_id(Math.toIntExact(userDto.getId()));
        service.update(id, reviewsDto);
        return new RedirectView("/reviews/");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MENTOR') or hasRole('INTERNSHIP')")
    public RedirectView deleteMentor(@PathVariable Long id) {
        service.delete(id);
        return new RedirectView("/reviews/");
    }
}
