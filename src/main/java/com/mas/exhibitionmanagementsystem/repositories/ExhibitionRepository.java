package com.mas.exhibitionmanagementsystem.repositories;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    List<Exhibition> findAll();
    Optional<Exhibition> findById(Long id);
    Optional<List<Exhibition>> findAllByStartDateAfter(LocalDate startDate);
    int countByLocationAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Location location, LocalDate date, LocalDate date2);
}
