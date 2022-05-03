package com.kszydlo1.ticket_booking_app.views;

import com.kszydlo1.ticket_booking_app.Constants;
import com.kszydlo1.ticket_booking_app.model.database.Screening;
import com.kszydlo1.ticket_booking_app.model.requests.ScreeningsPeriodRequest;
import com.kszydlo1.ticket_booking_app.model.responses.ScreeningsPeriodResponse;
import com.kszydlo1.ticket_booking_app.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@RestController
public class ScreeningsView {
    private class StartEndDateException extends Exception {};

    @Autowired
    private ScreeningRepository screeningRepository;

    @GetMapping(value = "/screenings", produces = "application/json;charset=UTF-8")
    public Vector showScreenings(@RequestBody ScreeningsPeriodRequest sp){
        try {
            if (sp.getStartDate().after(sp.getEndDate()))
                throw new StartEndDateException();

            Vector response = getResponse(sp);
            response = getSortedResponse(response);

            return response;
        }
        catch (StartEndDateException e) {
            Vector response = new Vector<>();
            response.add(Constants.Views.START_END_DATE_EXCEPTION);
            return response;
        }
    }

    private Vector getResponse(ScreeningsPeriodRequest sp) {
        Vector response = new Vector<ScreeningsPeriodResponse>();
        List<Screening> screenings = (List<Screening>) screeningRepository.findAll()
                .stream()
                .filter(screening -> screening.getStartTime().after(sp.getStartDate())
                        && screening.getStartTime().before(sp.getEndDate()))
                .collect(Collectors.toList());
        for (Screening screening : screenings) {
            ScreeningsPeriodResponse screeningsPeriodResponse = new ScreeningsPeriodResponse();
            screeningsPeriodResponse.setScreeningId(screening.getScreeningId());
            screeningsPeriodResponse.setTitle(screening.getMovie().getTitle());
            screeningsPeriodResponse.setStartTime(screening.getStartTime());

            response.add(screeningsPeriodResponse);
        }
        return response;
    }

    private Vector getSortedResponse(Vector unsortedVec) {
        Vector sortedVec = new Vector<>(unsortedVec);
        sortedVec.sort(new Comparator<ScreeningsPeriodResponse>() {
            @Override
            public int compare(ScreeningsPeriodResponse s1, ScreeningsPeriodResponse s2) {
                if(s1.getTitle() == s2.getTitle()){
                    return s1.getStartTime().compareTo(s2.getStartTime());
                }
                else
                    return s1.getTitle().compareTo(s2.getTitle());
            }
        });
        return sortedVec;
    }
}
