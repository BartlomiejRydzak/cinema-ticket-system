package pl.pwr.cinematicketsystem.dto;

import lombok.Getter;

@Getter
public class MovieRequest {
    private String title;
    private String description;
    private Integer durationMinutes;
    private String image_url;
}
