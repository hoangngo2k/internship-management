package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.CompanyCardDto;
import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.model.CompanyCard;
import com.example.internshipmanagement.service.CompanyCardService;
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
@RequestMapping("/home/company-cards")
public class CompanyCardController {

    private final CompanyCardService service;
    private final UserService userService;

    public CompanyCardController(CompanyCardService service, UserService userService) {
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
        Page<CompanyCard> companyCardPage = service.getAll(pageable, "using_flg", searchForm.getKeyword(), page, size);
        int totalPages = companyCardPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("companyCardList", companyCardPage);
        return new ModelAndView("company-card/company-card-list");
    }

    @GetMapping("/save-form")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView saveMentorForm(Model model, CompanyCardDto companyCardDto) {
        model.addAttribute("companyCard", companyCardDto);
        return new ModelAndView("company-card/new-company-card");
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveMentor(@ModelAttribute("companyCard") CompanyCardDto companyCardDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        companyCardDto.setCreate_id(Math.toIntExact(userDto.getId()));
        companyCardDto.setModified_id(Math.toIntExact(userDto.getId()));
        service.save(companyCardDto);
        return new RedirectView("/home/company-cards/");
    }

    @GetMapping("/update-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateMentorFrom(Model model, @PathVariable String id) {
        CompanyCardDto companyCardDto = service.getCompanyCardById(id);
        model.addAttribute("companyCard", companyCardDto);
        return new ModelAndView("company-card/update-company-card");
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView updateMentor(@PathVariable String id, @Valid CompanyCardDto companyCardDto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            companyCardDto.setId(id);
            return new RedirectView("/home/company-cards/update-form/" + id);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        companyCardDto.setModified_id(Math.toIntExact(userDto.getId()));
        service.update(id, companyCardDto);
        return new RedirectView("/home/company-cards/");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteMentor(@PathVariable String id) {
        service.delete(id);
        return new RedirectView("/home/company-cards/");
    }
}
