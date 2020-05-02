package com.ouyanglol.helptest.mock;

import com.ouyanglol.helptest.service.TestService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ouyangduning
 * @date 2020/5/2 16:57
 */
@Slf4j
public class TestServiceMock implements TestService {
    @Override
    public void test() {
        log.info("test Mock");
    }

}
