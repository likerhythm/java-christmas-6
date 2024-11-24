package christmas.model;

import java.time.LocalDate;

public class DateRange {

    private final LocalDate startDate;
    private final LocalDate endDate;

    private DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static DateRange of(LocalDate startDate, LocalDate endDate) {
        return new DateRange(startDate, endDate);
    }

    public boolean isInRange(LocalDate date) {
        return startDate.isEqual(date) || startDate.isBefore(date)
                || endDate.isAfter(date) || endDate.isEqual(date);
    }
}
