package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.dto.UniversityDto;
import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.model.University;
import com.example.internshipmanagement.service.UniversityService;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/home/universities")
public class UniversityController {

    private final UniversityService service;
    private final UserService userService;

    public UniversityController(UniversityService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView getAllMentor(Model model,
                                     @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                     @ModelAttribute(value = "searchForm") SearchForm searchForm) {
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        Pageable pageable = null;
        Page<University> universityPage = service.getAll(pageable, "name", searchForm.getKeyword(), page, size);
        int totalPages = universityPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("universityList", universityPage);
        return new ModelAndView("university/university-list");
    }

    @GetMapping("/save-form")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView saveMentorForm(Model model, UniversityDto universityDto) {
        model.addAttribute("university", universityDto);
        return new ModelAndView("university/new-university");
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveMentor(@ModelAttribute("university") UniversityDto universityDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        universityDto.setCreate_id(Math.toIntExact(userDto.getId()));
        universityDto.setModified_id(Math.toIntExact(userDto.getId()));
        service.save(universityDto);
        return new RedirectView("/home/universities/");
    }

    @GetMapping("/update-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateMentorFrom(Model model, @PathVariable Long id) {
        UniversityDto universityDto = service.getUniversityById(id);
        model.addAttribute("university", universityDto);
        return new ModelAndView("university/update-university");
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView updateMentor(@PathVariable Long id, @Valid UniversityDto universityDto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            universityDto.setId(id);
            return new RedirectView("/update-form/" + id);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        universityDto.setModified_id(Math.toIntExact(userDto.getId()));
        service.update(id, universityDto);
        return new RedirectView("/home/universities/");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteMentor(@PathVariable Long id) {
        service.delete(id);
        return new RedirectView("/home/universities/");
    }
}
