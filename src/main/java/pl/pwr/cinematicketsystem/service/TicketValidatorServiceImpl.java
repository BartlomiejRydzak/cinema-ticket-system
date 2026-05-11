package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.TicketValidatorRequest;
import pl.pwr.cinematicketsystem.entity.TicketValidator;
import pl.pwr.cinematicketsystem.repository.TicketValidatorRepository;

@Service
public class TicketValidatorServiceImpl implements TicketValidatorService{

    private TicketValidatorRepository ticketValidatorRepository;

    @Autowired
    public TicketValidatorServiceImpl(TicketValidatorRepository ticketValidatorRepository) {
        this.ticketValidatorRepository = ticketValidatorRepository;
    }

    @Override
    public TicketValidator login(TicketValidatorRequest ticketValidatorRequest) {
        TicketValidator ticketValidator = ticketValidatorRepository.findByLogin(ticketValidatorRequest.getLogin());
        if (ticketValidator.getPassword().equals(ticketValidatorRequest.getPassword()))
            return ticketValidator;
        else {
            return null;
        }
    }
}
