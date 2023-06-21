package com.mas.exhibitionmanagementsystem.utilities.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Converter class for converting LocalDate to Date and vice versa
 * This class is used for automatic conversion between LocalDate and Date types
 */
@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, Date> {
    /**
     * Converts LocalDate object to Date object
     * @param localDate  the entity attribute value to be converted
     * @return Date object or null
     */
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null ? null : Date.valueOf(localDate));
    }

    /**
     * Converts Date object to LocalDate object
     * @param date  the data from the database column to be
     *                converted
     * @return LocalDate object or null
     */
    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return (date == null ? null : date.toLocalDate());
    }
}
