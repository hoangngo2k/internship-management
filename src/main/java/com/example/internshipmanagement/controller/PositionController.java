package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.PositionDto;
import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.model.Position;
import com.example.internshipmanagement.service.PositionService;
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
@RequestMapping("/home-admin/positions")
public class PositionController {

    private final PositionService service;
    private final UserService userService;

    public PositionController(PositionService service, UserService userService) {
        this.service = service;
        this.userService = userService;
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
        Page<Position> positionPage = service.getAll(pageable, searchForm.getKeyword(), page, size, sort, field);
        int totalPages = positionPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("positionList", positionPage);
        return new ModelAndView("position/position-list");
    }

    @GetMapping("/save-form")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView saveMentorForm(Model model, PositionDto positionDto) {
        model.addAttribute("position", positionDto);
        return new ModelAndView("position/new-position");
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveMentor(@ModelAttribute("position") PositionDto positionDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        positionDto.setCreateId(Math.toIntExact(userDto.getId()));
        positionDto.setModifiedId(Math.toIntExact(userDto.getId()));
        service.save(positionDto);
        return new RedirectView("/home-admin/positions/");
    }

    @GetMapping("/update-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateMentorFrom(Model model, @PathVariable Long id) {
        PositionDto positionDto = service.getPositionById(id);
        model.addAttribute("position", positionDto);
        return new ModelAndView("position/update-position");
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView updateMentor(@PathVariable Long id, @Valid PositionDto positionDto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            positionDto.setId(id);
            return new RedirectView("/home-admin/positions/update-form/" + id);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        positionDto.setModifiedId(Math.toIntExact(userDto.getId()));
        service.update(id, positionDto);
        return new RedirectView("/home-admin/positions/");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteMentor(@PathVariable Long id) {
        service.delete(id);
        return new RedirectView("/home-admin/positions/");
    }
}
