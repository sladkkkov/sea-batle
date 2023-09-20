package ru.sladkkov.seabatle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sladkkov.seabatle.service.ShipService;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SeaBattleApp implements CommandLineRunner {

    private final ShipService shipService;

    public static void main(String[] args) {
        SpringApplication.run(SeaBattleApp.class, args);
    }

    @Override
    public void run(String... args) {
        shipService.play();
    }
}
