package com.ouyanglol.helptest;

import com.ouyanglol.helptest.annotation.EnableMock;
import com.ouyanglol.helptest.annotation.HelpMockBean;
import com.ouyanglol.helptest.mock.TestMock;
import com.ouyanglol.helptest.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableMock
class HelpTestApplicationTests {

    @HelpMockBean(beanClass = TestService.class)
    private TestMock testMock;

    @Autowired
    private TestService testService;

    @Test
    void contextLoads() {
        System.out.println(testService.test());
    }

}
