package com.mediscreen.assessment.util;

import com.mediscreen.assessment.constant.AssessmentConstant;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static java.time.LocalDate.now;

@Service
public class Calculator {



    public int calculateAge(LocalDate birthdate) {

        return Period.between(birthdate, now()).getYears();
    }


    public boolean isOlderThenThirty(LocalDate birthdate) {

        return calculateAge(birthdate) >= 30;

    }

    public int calculateTriggersNumber(List<String> notes) {

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
