package christmas.model.event;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EventDates {

    public static final int EVENT_YEAR = 2024;
    public static final int EVENT_MONTH = 12;

    private final List<LocalDate> dates;

    private EventDates(List<LocalDate> dates) {
        this.dates = dates;
    }

    public static EventDates of(int... days) {
        List<LocalDate> dates = Arrays.stream(days)
                .mapToObj(day -> LocalDate.of(EVENT_YEAR, EVENT_MONTH, day))
                .collect(Collectors.toList());
        return new EventDates(dates);
    }

    public static EventDates ofRange(int startDay, int endDay) {
        List<LocalDate> dates = IntStream.range(startDay, endDay + 1)
                .boxed()
                .map(day -> LocalDate.of(EVENT_YEAR, EVENT_MONTH, day))
                .collect(Collectors.toList());
        return new EventDates(dates);
    }

    public boolean isInclude(LocalDate date) {
        return dates.contains(date);
    }
}
