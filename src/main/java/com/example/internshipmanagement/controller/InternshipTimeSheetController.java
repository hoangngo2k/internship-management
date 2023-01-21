package com.example.internshipmanagement.controller;

import com.example.internshipmanagement.dto.InternshipTimeSheetDto;
import com.example.internshipmanagement.dto.SearchForm;
import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.model.InternshipTimeSheet;
import com.example.internshipmanagement.service.InternshipTimeSheetService;
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
@RequestMapping("/home/time-sheets")
public class InternshipTimeSheetController {

    private final InternshipTimeSheetService service;
    private final UserService userService;

    public InternshipTimeSheetController(InternshipTimeSheetService service, UserService userService) {
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
        Page<InternshipTimeSheet> internshipTimeSheetPage = service.getAll(pageable, "time", searchForm.getKeyword(), page, size);
        int totalPages = internshipTimeSheetPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("timesheetList", internshipTimeSheetPage);
        return new ModelAndView("time-sheet/time-sheet-list");
    }

    @GetMapping("/save-form")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView saveMentorForm(Model model, InternshipTimeSheetDto timeSheetDto) {
        model.addAttribute("timesheet", timeSheetDto);
        return new ModelAndView("time-sheet/new-time-sheet");
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveMentor(@ModelAttribute("timesheet") InternshipTimeSheetDto timeSheetDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        timeSheetDto.setCreate_id(Math.toIntExact(userDto.getId()));
        timeSheetDto.setModified_id(Math.toIntExact(userDto.getId()));
        service.save(timeSheetDto);
        return new RedirectView("/home/time-sheets/");
    }

    @GetMapping("/update-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateMentorFrom(Model model, @PathVariable Long id) {
        InternshipTimeSheetDto timeSheetDto = service.getTimesheetById(id);
        model.addAttribute("timesheet", timeSheetDto);
        return new ModelAndView("time-sheet/update-time-sheet");
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView updateMentor(@PathVariable Long id, @Valid InternshipTimeSheetDto timeSheetDto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            timeSheetDto.setId(id);
            return new RedirectView("/update-form/" + id);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userDto = userService.getUserByUsername(userDto.getUsername());
        timeSheetDto.setModified_id(Math.toIntExact(userDto.getId()));
        service.update(id, timeSheetDto);
        return new RedirectView("/home/time-sheets/");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteMentor(@PathVariable Long id) {
        service.delete(id);
        return new RedirectView("/home/time-sheets/");
    }
}
