package ru.avdeev.order_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest()
@ActiveProfiles("dev")
class OrderServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
