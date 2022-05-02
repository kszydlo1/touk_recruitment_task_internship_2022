package com.kszydlo1.ticket_booking_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.kszydlo1.ticket_booking_app*"})
@EntityScan("com.kszydlo1.ticket_booking_app*")
@EnableJpaRepositories("com.kszydlo1.ticket_booking_app*")
public class TicketBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingAppApplication.class, args);
	}

}
