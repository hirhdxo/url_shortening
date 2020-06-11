package com.task.musinsa;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
public abstract class BaseSpringTest {
    protected static final String VALID_TEST_URL = "http://ktko.tistory.com";
    protected static final String VALID_TEST_SHORT_URL = "12345678";
    protected static final String INVALID_TEST_URL = "http://localhost:8080";
    protected static final String INVALID_TEST_SHORT_URL = "123456789";

    protected static final String SERVICE_DOMAIN = "http://musinsa.com/";

}
