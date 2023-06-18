package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.models.dto.ExhibitionDto;
import com.mas.exhibitionmanagementsystem.repositories.ExhibitionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;

    public ExhibitionService(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    public List<Exhibition> getAllExhibitions() {
        /*List<ExhibitionDto> exhibitionDtoList = new ArrayList<>();
        exhibitionRepository.findAll().forEach( exhibition -> {
            ExhibitionDto dto = new ExhibitionDto();
            dto.setName(exhibition.getName());
            dto.setStartDate(exhibition.getStartDate());
            dto.setEndDate(exhibition.getEndDate());
            exhibitionDtoList.add(dto);
        });*/
        return exhibitionRepository.findAll();
    }

    public Optional<Exhibition> getExhibitionById(Long id) {
        return exhibitionRepository.findById(id);
    }

    public Optional<List<Exhibition>> getExhibitionsAfterStartDate(LocalDate startDate) {
        return exhibitionRepository.findAllByStartDateAfter(startDate);
    }
}
