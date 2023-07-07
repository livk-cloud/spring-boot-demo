/*
 * Copyright 2021 spring-boot-extension the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.livk.zookeeper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author livk
 */
@SpringBootTest
@AutoConfigureMockMvc
class LockControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void lock() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(10);
		CountDownLatch countDownLatch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			String param = String.valueOf(i);
			service.submit(() -> {
				try {
					mockMvc.perform(get("/lock")
							.queryParam("id", param))
						.andExpect(status().isOk());
					countDownLatch.countDown();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		}
		countDownLatch.await();
		service.shutdown();
	}
}
