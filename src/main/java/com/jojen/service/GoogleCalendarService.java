package com.jojen.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoogleCalendarService {
    @Value("${google.calendar.id}")
    String calendarId;
    private com.google.api.services.calendar.Calendar googleCalendar;

    public GoogleCalendarService(com.google.api.services.calendar.Calendar googleCalendar) {
        this.googleCalendar = googleCalendar;
    }


    public List<String> getUpcomingEvents() throws IOException {
        List<String> ret = new ArrayList<>();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date maxTime = Date.from(LocalDateTime.now().plusDays(7).toLocalDate().atStartOfDay(defaultZoneId).toInstant());

        Events events = googleCalendar.events().list(calendarId).setTimeMin(new DateTime(new Date())).setTimeMax(new DateTime(maxTime)).setMaxResults(10).execute();
        for (Event e : events.getItems()) {
            StringBuilder event = new StringBuilder();
            DateTime time = e.getStart().getDateTime();
            if (time == null) {
                event.append(LocalDate.parse(e.getStart().getDate().toStringRfc3339()).format(DateTimeFormatter.ofPattern("EEE")));
            } else {
                DateTimeFormatter f = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
                event.append(LocalDateTime.parse(e.getStart().getDateTime().toStringRfc3339(),f).format(DateTimeFormatter.ofPattern("EEE HH:mm")));
                event.append(" Uhr");
            }
            event.append(" | ");
            event.append(e.getSummary());

            ret.add(event.toString());
        }

        return ret;

    }

}
