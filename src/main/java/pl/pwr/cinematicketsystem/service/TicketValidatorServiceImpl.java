package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.SeatResponse;
import pl.pwr.cinematicketsystem.dto.TicketValidatorRequest;
import pl.pwr.cinematicketsystem.dto.TicketValidatorResponse;
import pl.pwr.cinematicketsystem.entity.Ticket;
import pl.pwr.cinematicketsystem.entity.TicketState;
import pl.pwr.cinematicketsystem.entity.TicketValidator;
import pl.pwr.cinematicketsystem.repository.TicketRepository;
import pl.pwr.cinematicketsystem.repository.TicketValidatorRepository;

import java.util.List;

@Service
public class TicketValidatorServiceImpl implements TicketValidatorService{

    private TicketValidatorRepository ticketValidatorRepository;
    private TicketRepository ticketRepository;
    private ShowService showService;

    @Autowired
    public TicketValidatorServiceImpl(TicketValidatorRepository ticketValidatorRepository, TicketRepository ticketRepository, ShowService showService) {
        this.ticketValidatorRepository = ticketValidatorRepository;
        this.ticketRepository = ticketRepository;
        this.showService = showService;
    }

    @Override
    public TicketValidator login(TicketValidatorRequest ticketValidatorRequest) {
        TicketValidator ticketValidator = ticketValidatorRepository.findByUsername(ticketValidatorRequest.getUsername());
        if (ticketValidator.getPassword().equals(ticketValidatorRequest.getPassword()))
            return ticketValidator;
        else {
            return null;
        }
    }

    @Override
    public void scanTicket(String code) {
        Ticket ticket = ticketRepository.findByCode(code);
        if (ticket.getState().equals(TicketState.VALID)){
            ticket.setState(TicketState.USED);
        }
    }

    @Override
    public TicketValidatorResponse getInfo(String ticketCode) {
        Ticket ticket = ticketRepository.findByCode(ticketCode);
        List<SeatResponse> seats = showService.getSeats(ticket.getShow().getId());
        Integer numberOfReservedSeats = 0;
        for(SeatResponse seat : seats){
            if(seat.getReserved()){
                numberOfReservedSeats += 1;
            }
        }

        return TicketValidatorResponse.builder()
                .ticketCode(ticketCode)
                .ticketState(ticket.getState())
                .numberOfReservedSeats(numberOfReservedSeats)
                .showDate(ticket.getShow().getDate())
                .seats(seats)
                .build();
    }
}
