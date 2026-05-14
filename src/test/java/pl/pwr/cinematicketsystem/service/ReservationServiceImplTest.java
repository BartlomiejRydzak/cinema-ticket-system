package pl.pwr.cinematicketsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pwr.cinematicketsystem.dto.ReservationRequest;
import pl.pwr.cinematicketsystem.dto.ReservationResponse;
import pl.pwr.cinematicketsystem.entity.Reservation;
import pl.pwr.cinematicketsystem.entity.Seat;
import pl.pwr.cinematicketsystem.entity.Show;
import pl.pwr.cinematicketsystem.repository.ReservationRepository;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    SeatService seatService;

    @Mock
    ShowService showService;

    @Test
    void addReservation_createsTickets_andReturnsResponse() {
        ReservationRequest req = mock(ReservationRequest.class);
        when(req.getShowId()).thenReturn(2);
        when(req.getSeatIds()).thenReturn(List.of(5, 6));

        Show show = new Show();
        show.setId(2);
        when(showService.getShowById(2)).thenReturn(show);

        Seat s1 = new Seat();
        s1.setId(5);
        Seat s2 = new Seat();
        s2.setId(6);
        when(seatService.getSeatById(5)).thenReturn(s1);
        when(seatService.getSeatById(6)).thenReturn(s2);

        when(reservationRepository.save(any())).thenAnswer(
            (org.mockito.stubbing.Answer<Reservation>) invocation ->
                invocation.getArgument(0)
        );

        ReservationServiceImpl svc = new ReservationServiceImpl(
            reservationRepository,
            seatService,
            showService
        );
        ReservationResponse resp = svc.addReservation(req);

        // id may be null in unit test since repository is mocked; check code generated and save used
        assertNotNull(resp.getCode());
        verify(reservationRepository, times(1)).save(any());
    }
}
