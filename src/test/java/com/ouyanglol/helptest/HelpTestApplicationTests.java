package com.ouyanglol.helptest;

import com.ouyanglol.helptest.annotation.EnableMock;
import com.ouyanglol.helptest.annotation.HelpMockBean;
import com.ouyanglol.helptest.mock.TestServiceMock;
import com.ouyanglol.helptest.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestMainApplication.class)
@EnableMock
class HelpTestApplicationTests {

    @Autowired
    private TestService testService;

    @HelpMockBean(beanClass = TestServiceMock.class)
    private TestService mock;

    @Test
    void contextLoads() {
        testService.test();
    }

}
