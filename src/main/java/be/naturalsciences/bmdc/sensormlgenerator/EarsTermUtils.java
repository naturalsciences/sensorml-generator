/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.naturalsciences.bmdc.sensormlgenerator;

import be.naturalsciences.bmdc.cruise.model.IEvent;
import be.naturalsciences.bmdc.cruise.model.ILinkedDataTerm;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thomas
 */
public class EarsTermUtils {

    public static class ProcessAction {

        public String process;
        public String action;

        public ProcessAction(String process, String action) {
            this.process = process;
            this.action = action;
        }
    }

    public static class Period {

        public OffsetDateTime start;
        public OffsetDateTime end;

        public Period(OffsetDateTime start, OffsetDateTime end) {
            this.start = start;
            this.end = end;
        }

    }

    /**
     * *
     * Find the Period p between 2 events defined by their process, the start
     * action and the end action. Takes the broadest timerange (eager) found in
     * the events list. The events list must be constrained by the tools. Action
     * identifiers/names can be split by a pipe | to find multiple variants.
     *
     * @param events
     * @param processNameOrIdentifier
     * @param startActionNameOrIdentifier
     * @param endActionNameOrIdentifier
     * @return
     */
    public static Period findEventTimerangeEager(List<IEvent> events, ProcessAction[] startEvents, ProcessAction[] endEvents) {
        OffsetDateTime start = null;
        OffsetDateTime end = null;
        Collections.sort(events, new Comparator<IEvent>() { //set them chronologically
            @Override
            public int compare(IEvent e1, IEvent e2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return e1.getTimeStamp().isAfter(e2.getTimeStamp()) ? -1 : (e1.getTimeStamp().isBefore(e2.getTimeStamp())) ? 1 : 0;
            }
        });

        for (IEvent event : events) {
            for (ProcessAction startEvent : startEvents) {
                if (ILinkedDataTerm.identifierOrNameMatches(event.getProcess(), startEvent.process) && ILinkedDataTerm.identifierOrNameMatches(event.getAction(), startEvent.action)) {
                    start = event.getTimeStamp();
                    break;
                }
            }

        }

        Collections.sort(events, new Comparator<IEvent>() { //set them chronologically
            @Override
            public int compare(IEvent e1, IEvent e2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return e1.getTimeStamp().isAfter(e2.getTimeStamp()) ? 1 : (e1.getTimeStamp().isBefore(e2.getTimeStamp())) ? -1 : 0;
            }
        });

        for (IEvent event : events) {
            for (ProcessAction endEvent : endEvents) {
                if (ILinkedDataTerm.identifierOrNameMatches(event.getProcess(), endEvent.process) && ILinkedDataTerm.identifierOrNameMatches(event.getAction(), endEvent.action)) {
                    end = event.getTimeStamp();
                    break;
                }
            }
        }

        return new Period(start, end);
    }

}
