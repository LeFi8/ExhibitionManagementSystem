package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.services.ExhibitionService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    public ExhibitionController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @GetMapping("/exhibitions")
    public String getAllExhibitions(Model model) {
        /*List<Exhibition> exhibitions = exhibitionService.getAllExhibitions();
        model.addAttribute("exhibitions", exhibitions);*/
        return "exhibitions";
    }

    @GetMapping("exhibitions/{id}")
    public String getExhibition(Model model) {
        //TODO: logic
        return "exhibition-details";
    }

    @GetMapping("exhibitions/{id}/artworks")
    public String getArtWorks(Model model) {
        //TODO: logic
        return "exhibition-artworks";
    }
}
