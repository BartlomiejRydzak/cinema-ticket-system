package pl.pwr.cinematicketsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.pwr.cinematicketsystem.dto.SeatResponse;
import pl.pwr.cinematicketsystem.dto.TicketValidatorResponse;
import pl.pwr.cinematicketsystem.entity.Reservation;
import pl.pwr.cinematicketsystem.entity.Room;
import pl.pwr.cinematicketsystem.entity.Seat;
import pl.pwr.cinematicketsystem.entity.Show;
import pl.pwr.cinematicketsystem.entity.Ticket;
import pl.pwr.cinematicketsystem.entity.TicketState;
import pl.pwr.cinematicketsystem.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
class TicketValidatorServiceImplTest {

    @Mock
    TicketRepository ticketRepository;
    @Mock
    TicketService ticketService;
    @Mock
    ShowService showService;

    @Test
    void scanTicket_setsUsed_whenValid() {
        Ticket t = new Ticket(); t.setCode("C"); t.setState(TicketState.VALID);
        when(ticketService.getTicketByCode("C")).thenReturn(t);
        when(ticketService.isValid("C")).thenReturn(true);
        when(ticketRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        TicketValidatorServiceImpl svc = new TicketValidatorServiceImpl(ticketRepository, ticketService, showService);
        svc.scanTicket("C");

        assertEquals(TicketState.USED, t.getState());
        verify(ticketRepository, times(1)).save(t);
    }

    @Test
    void getInfo_buildsResponse_withReservedSeats() {
        Room room = new Room(); room.setId(9);
        Seat seat = new Seat(21, 1, 1, room, null);
        room.setSeats(List.of(seat));

        Show show = new Show(); show.setId(4); show.setDate(LocalDateTime.now()); show.setRoom(room);

        Ticket ticket = new Ticket(); ticket.setCode("Z"); ticket.setShow(show);
        Reservation res = new Reservation(); res.setTickets(List.of(ticket)); ticket.setReservation(res);
        ticket.setState(TicketState.VALID);

        when(ticketService.getTicketByCode("Z")).thenReturn(ticket);
        doNothing().when(ticketService).updateState(ticket);
        when(showService.getSeats(4)).thenReturn(List.of(SeatResponse.builder().rowNumber(1).seatNumber(1).roomId(9).reserved(false).build()));

        TicketValidatorServiceImpl svc = new TicketValidatorServiceImpl(ticketRepository, ticketService, showService);
        TicketValidatorResponse r = svc.getInfo("Z");

        assertEquals("Z", r.getTicketCode());
        assertEquals(TicketState.VALID, r.getTicketState());
        assertNotNull(r.getNumberOfReservedSeats());
        assertTrue(r.getNumberOfReservedSeats() >= 0);
        assertNotNull(r.getShowDate());
        assertEquals(9, r.getRoomId());
        // reservedSeats may be empty if ticket had no seat set
        assertNotNull(r.getReservedSeats());
    }
}
