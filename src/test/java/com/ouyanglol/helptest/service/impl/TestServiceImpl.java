package com.ouyanglol.helptest.service.impl;

import com.ouyanglol.helptest.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ouyangduning
 * @date 2020/5/2 16:56
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        log.info("test");
    }
}
