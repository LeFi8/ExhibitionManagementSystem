package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.services.ExhibitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    public ExhibitionController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @GetMapping("/exhibitions")
    public String getAllOngoingExhibitions(Model model) {
        List<Exhibition> exhibitions = exhibitionService.getAllExhibitions();
        model.addAttribute("exhibitions", exhibitions);
        return "exhibitions";
    }

    @GetMapping("exhibition/{id}")
    public String getExhibition(@PathVariable Long id, Model model) {
        Exhibition exhibition = exhibitionService.getExhibitionById(id).orElse(new Exhibition());
        model.addAttribute("exhibition", exhibition);
        return "exhibition-details";
    }

    @GetMapping("exhibitions/{id}/artworks")
    public String getArtWorks(Model model) {
        //TODO: logic
        return "exhibition-artworks";
    }
}
