package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.services.ExhibitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Controller class for managing exhibitions
 */
@Controller
public class ExhibitionController {
    private final ExhibitionService exhibitionService;
    public ExhibitionController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    /**
     * Get all exhibitions and display them in the view,
     * then check if there are exhibitions available or not
     *
     * @param model used for passing data to the view
     * @return path to the view, depending on exhibitions availability
     */
    @GetMapping("/exhibitions")
    public String getAllExhibitions(Model model) {
        List<Exhibition> exhibitions = exhibitionService.getAllExhibitions();
        if (exhibitions.isEmpty()) return "no-exhibitions";
        model.addAttribute("exhibitions", exhibitions);
        return "exhibitions";
    }

    /**
     * Get specific exhibition based on provided id
     *
     * @param id used for getting the desired exhibition
     * @param model used for passing data to the view
     * @return path to the view
     */
    @GetMapping("exhibition/{id}")
    public String getExhibition(@PathVariable Long id, Model model) {
        Exhibition exhibition = exhibitionService.getExhibitionById(id).orElse(new Exhibition());
        model.addAttribute("exhibition", exhibition);
        return "exhibition-details";
    }

    /**
     * Get all artworks related to the given exhibition
     * and display them in the view
     *
     * @param id of chosen exhibition
     * @param model used for passing data to the view
     * @return path to the view
     */
    @GetMapping("exhibition/{id}/artworks")
    public String getArtWorks(@PathVariable Long id, Model model) {
        Exhibition exhibition = exhibitionService.getExhibitionById(id).orElse(new Exhibition());
        model.addAttribute("exhibition", exhibition);
        return "exhibition-artworks";
    }
}
