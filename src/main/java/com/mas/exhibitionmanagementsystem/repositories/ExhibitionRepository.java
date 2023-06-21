package com.mas.exhibitionmanagementsystem.repositories;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Exhibition entities
 */
@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    /**
     * Retrieves all exhibitions with start dates after the specified date.
     *
     * @param startDate the start date of an exhibition
     * @return Optional containing a list of exhibitions, empty Optional if none found
     */
    Optional<List<Exhibition>> findAllByStartDateAfter(LocalDate startDate);

    /**
     * Counts the number of exhibitions with a specific location, which start date is
     * less than or equal to the specified date, and which end date is greater than
     * or equal to the specified date
     *
     * @param location exhibition's location
     * @param date date for comparings
     * @param date2 date for comparings
     * @return number of exhibitions that matched the condition
     */
    int countByLocationAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Location location, LocalDate date, LocalDate date2);

    /**
     * Counts the number of exhibitions with a specific location, which start date is
     * less than or equal to the specified date
     *
     * @param location exhibition's location
     * @param date date for comparings
     * @return number of exhibitions that matched the condition
     */
    int countByLocationAndStartDateLessThanEqual(Location location, LocalDate date);
}
