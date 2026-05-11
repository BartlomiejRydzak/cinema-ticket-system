package pl.pwr.cinematicketsystem.dto;

import lombok.Getter;

@Getter
public class TicketValidatorRequest {
    private String login;
    private String password;
}
