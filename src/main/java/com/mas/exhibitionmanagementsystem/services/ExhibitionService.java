package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.repositories.ExhibitionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing exhibitions
 */
@Service
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;

    public ExhibitionService(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    /**
     * Retrieves all exhibitions
     * @return list of all exhibitions
     */
    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    /**
     * Retrieves an exhibition with given id
     *
     * @param id of the exhibition
     * @return Optional containing the exhibition, empty Optional if none found
     */
    public Optional<Exhibition> getExhibitionById(Long id) {
        return exhibitionRepository.findById(id);
    }

    /**
     * Calculates the available space on specific exhibition for a given date
     *
     * @param exhibition to check for available space
     * @param date to check for available space
     * @return the available space on the exhibition
     */
    public int spaceOnExhibition(Exhibition exhibition, LocalDate date) {
        int numberOfExhibitionsInLocation;

        if (exhibition.getEndDate() == null) {
            numberOfExhibitionsInLocation =
                    exhibitionRepository.countByLocationAndStartDateLessThanEqual(exhibition.getLocation(), date);
        } else numberOfExhibitionsInLocation =
                exhibitionRepository.countByLocationAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                                exhibition.getLocation(), date, date);

        if (numberOfExhibitionsInLocation == 0) return 0;

        return exhibition.getLocation().getMaxCapacity() / numberOfExhibitionsInLocation;
    }
}
