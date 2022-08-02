package com.mediscreen.assessment.util;

import com.mediscreen.assessment.constant.AssessmentConstant;
import com.mediscreen.assessment.controller.AssessmentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static java.time.LocalDate.now;

/**
 * the class of calculations
 */
@Service
public class Calculator {
    private final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);
    /**
     * calculates the age from a given date of birth
     * @param birthdate : date of birth
     * @return age
     */
    public int calculateAge(LocalDate birthdate) {
        LOGGER.debug("calculation of the age since the date {}",birthdate);
        return Period.between(birthdate, now()).getYears();
    }

    /**
     * check if the age is more than 30 years since a given date of birth
     * @param birthdate date of birth
     * @return true if over or equal 30
     */
    public boolean isOlderThenThirty(LocalDate birthdate) {
        LOGGER.debug("checking if is older then 30 for birthdate: {}",birthdate);
        return calculateAge(birthdate) >= 30;

    }

    /**
     *calculate the number of triggers in a given list
     * @param notes note list
     * @return number of triggers
     */
    public int calculateTriggersNumber(List<String> notes) {
        LOGGER.debug("calculation of number of triggers");
        Set<String> patientsTerminologyTriggers = new HashSet<>();
       notes.forEach(n -> {
                    AssessmentConstant.TERMINOLOGY_TRIGGERS.forEach(t -> {
                        if (n.toLowerCase(Locale.ROOT).contains(t.toLowerCase(Locale.ROOT))) {
                            patientsTerminologyTriggers.add(t);
                        }
                    });
                }
        );
        return patientsTerminologyTriggers.size();
    }
}
