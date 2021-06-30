package com.gridnine.testing;
import com.gridnine.testing.Service.FlightFilterImpl;
import com.gridnine.testing.Service.FlightFilterService;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.utils.FlightBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List <Flight> list = FlightBuilder.createFlights();
        System.out.println(list);
        FlightFilterService flightFilterService = new FlightFilterImpl();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        informer();
        try {
            System.out.println("Введите номер фильтра");
            String answer = bufferedReader.readLine();
            if (answer .equals("1")){
                printFlight(flightFilterService.departureUntilTheCurrentTime(list , LocalDateTime.now()));
            }
            if (answer.equals("2")){
                printFlight(flightFilterService.segmentsWithArrivalDateEarlierThanDepartureDate(list));
            }
            if (answer.equals("3")){
                printFlight(flightFilterService.TotalTimeSpentOnTheGroundExceedsTwoHours(list));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void informer() {
        System.out.println("Какой фильтр следует применить?\n" + "1 - вылет до текущего момента времени\n"
                + "2 - имеются сегменты с датой прилёта раньше даты вылета\n" + "3 - общее время, проведённое на земле превышает два часа\n");
    }

    public static void printFlight (List <Flight> flights){
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}
