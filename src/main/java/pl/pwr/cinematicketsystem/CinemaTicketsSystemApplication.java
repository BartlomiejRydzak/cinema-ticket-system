package pl.pwr.cinematicketsystem;

import java.time.Clock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaTicketsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaTicketsSystemApplication.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
