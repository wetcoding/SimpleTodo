package com.wetcoding.simpletodo.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testing")
public abstract class AbstractIntegrationTest {
    @LocalServerPort
    protected Integer port;
}
