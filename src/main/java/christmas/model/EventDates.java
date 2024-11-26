package christmas.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EventDates {

    private final List<LocalDate> dates;

    private EventDates(List<LocalDate> dates) {
        this.dates = dates;
    }

    public static EventDates of(int... days) {
        List<LocalDate> dates = Arrays.stream(days)
                .mapToObj(day -> LocalDate.of(2024, 12, day))
                .collect(Collectors.toList());
        return new EventDates(dates);
    }

    public static EventDates ofRange(int startDay, int endDay) {
        List<LocalDate> dates = IntStream.range(startDay, endDay + 1)
                .boxed()
                .map(day -> LocalDate.of(2024, 12, day))
                .collect(Collectors.toList());
        return new EventDates(dates);
    }

    public boolean isInclude(LocalDate date) {
        return dates.contains(date);
    }
}
