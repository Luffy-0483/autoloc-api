package tn.esprit.autoloc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.pattern.observer.ReservationObserver;
import tn.esprit.autoloc.pattern.observer.ReservationSubject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests du Design Pattern Observer - ReservationSubject & ReservationObserver")
class ReservationObserverTest {

    static class MockObserver implements ReservationObserver {
        final List<String> eventsReceived = new ArrayList<>();
        final String canal;

        MockObserver(String canal) {
            this.canal = canal;
        }

        @Override
        public void update(Reservation reservation, String evenement) {
            eventsReceived.add(canal + ":" + evenement);
        }

        @Override
        public String getCanal() {
            return canal;
        }
    }

    @Test
    @DisplayName("Le Sujet doit notifier l'ensemble des observateurs enregistrés")
    void testNotificationObservateurs() {
        MockObserver emailObs = new MockObserver("EMAIL");
        MockObserver smsObs = new MockObserver("SMS");
        MockObserver auditObs = new MockObserver("AUDIT");

        ReservationSubject subject = new ReservationSubject(List.of(emailObs, smsObs));
        subject.attacher(auditObs);

        assertEquals(3, subject.getObservers().size());

        Reservation r = new Reservation();
        r.setIdReservation(42L);
        Client c = new Client();
        c.setNom("Ben Salah");
        c.setEmail("bensalah@example.com");
        r.setClient(c);

        subject.notifierObservateurs(r, "CREATION");

        assertEquals(1, emailObs.eventsReceived.size());
        assertEquals("EMAIL:CREATION", emailObs.eventsReceived.get(0));

        assertEquals(1, smsObs.eventsReceived.size());
        assertEquals("SMS:CREATION", smsObs.eventsReceived.get(0));

        assertEquals(1, auditObs.eventsReceived.size());
        assertEquals("AUDIT:CREATION", auditObs.eventsReceived.get(0));

        // Test detacher
        subject.detacher(smsObs);
        subject.notifierObservateurs(r, "ANNULATION");

        assertEquals(2, emailObs.eventsReceived.size());
        assertEquals(1, smsObs.eventsReceived.size()); // inchangé
        assertEquals(2, auditObs.eventsReceived.size());
    }
}
