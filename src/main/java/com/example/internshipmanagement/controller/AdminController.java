package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.model.User;
import com.example.internshipmanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/home-admin/admins")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView getAdminList(Model model,
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
        Page<User> adminPage = userService.getAll(pageable, searchForm.getKeyword(), page, size, sort, field);
        int totalPages = adminPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("adminList", adminPage);
        return new ModelAndView("admin/admin-list");
    }
}
