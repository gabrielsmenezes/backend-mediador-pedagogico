package com.example.backapi.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AsyncTaskConfigTest {

    @Autowired
    AsyncTaskConfig asyncTaskConfig;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void apagarTodasAsTurmas() {
        asyncTaskConfig.apagarTodasAsTurmas();
        assertTrue(asyncTaskConfig.turmaService.findAll().isEmpty());
    }
}