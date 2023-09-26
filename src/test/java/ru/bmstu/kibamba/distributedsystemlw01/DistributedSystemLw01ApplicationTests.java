package ru.bmstu.kibamba.distributedsystemlw01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bmstu.kibamba.distributedsystemlw01.config.DatabaseTestConfiguration;
import ru.bmstu.kibamba.distributedsystemlw01.controller.PersonController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {DatabaseTestConfiguration.class})
class DistributedSystemLw01ApplicationTests {

    @Autowired
    private PersonController personController;

    @Test
    void runApp() {
        assertThat(personController).isNotNull();
    }

}
