package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.repositories.ExhibitionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;

    public ExhibitionService(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    public Optional<Exhibition> getExhibitionById(Long id) {
        return exhibitionRepository.findById(id);
    }

    public Optional<List<Exhibition>> getExhibitionsAfterStartDate(LocalDate startDate) {
        return exhibitionRepository.findAllByStartDateAfter(startDate);
    }

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
