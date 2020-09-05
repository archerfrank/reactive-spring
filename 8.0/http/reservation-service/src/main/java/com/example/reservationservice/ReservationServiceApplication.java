package com.example.reservationservice;

import io.r2dbc.spi.ConnectionFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@AllArgsConstructor
public class ReservationServiceApplication {


  public static void main(String[] args) {
    SpringApplication.run(ReservationServiceApplication.class, args);
  }
  private DatabaseClient databaseClient;
  @Bean
  RouterFunction<ServerResponse> routes() {
    return route()
            .GET("/reservations", r -> ok().body(databaseClient.select()
                    .from("reservation")
                    .as(Reservation.class).fetch().all(),Reservation.class))
            .build();
  }

}

//interface ReservationRepository extends ReactiveCrudRepository<Reservation, Integer> {
//
////  @Query("select * from reservation where name = $1 ")
////  Flux<Reservation> findByName(String name);
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Reservation {
  @Id
  private Integer id;
  private String name;
}

