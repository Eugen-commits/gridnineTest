package com.gridnine.testing.Service;
import com.gridnine.testing.entity.Flight;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightFilterService {
    List <Flight> departureUntilTheCurrentTime (List <Flight> flights , LocalDateTime currentTime);
    List <Flight> segmentsWithArrivalDateEarlierThanDepartureDate (List <Flight> flights);
    List <Flight> TotalTimeSpentOnTheGroundExceedsTwoHours (List <Flight> flights);
}
