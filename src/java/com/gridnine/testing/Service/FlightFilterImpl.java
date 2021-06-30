package com.gridnine.testing.Service;

import com.gridnine.testing.entity.Flight;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterImpl implements FlightFilterService{

    @Override
    public List<Flight> departureUntilTheCurrentTime(List<Flight> flights, LocalDateTime currentTime) {
       List <Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {
            for (int j = 0; j < flights.get(i).getSegments().size() ; j++) {
                LocalDateTime departure = flights.get(i).getSegments().get(j).getDepartureDate();
                if (departure.isBefore(currentTime)){
                    result.add(flights.get(i));
                }
            }
        }
       return result;
    }

    @Override
    public List<Flight> segmentsWithArrivalDateEarlierThanDepartureDate(List<Flight> flights) {
        List <Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {
            for (int j = 0; j < flights.get(i).getSegments().size(); j++) {
                LocalDateTime arrival = flights.get(i).getSegments().get(j).getArrivalDate();
                LocalDateTime departure = flights.get(i).getSegments().get(j).getDepartureDate();
               if (arrival.isBefore(departure)){
                   result.add(flights.get(i));
               }
            }
        }
        return result;
    }

    @Override
    public List<Flight> TotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights) {
        List <Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.size() ; i++) {
            if (flights.get(i).getSegments().size() > 1){
                for (int j = 0; j <  flights.get(i).getSegments().size() -1; j++) {
                         LocalDateTime first = flights.get(i).getSegments().get(j).getArrivalDate();
                         LocalDateTime second = flights.get(i).getSegments().get(j + 1).getDepartureDate();
                         Long duration = first.until(second , ChronoUnit.HOURS);
                    if (duration > 2) {
                        result.add( flights.get(i));
                    }
                }
            }
        }
        return result;
    }
}
