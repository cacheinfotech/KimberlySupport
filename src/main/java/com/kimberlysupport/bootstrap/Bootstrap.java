package com.kimberlysupport.bootstrap;

import com.kimberlysupport.service.BootstrapService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class Bootstrap implements InitializingBean {

    @Autowired
    private BootstrapService bootstrapService;

    @Transactional
    @Override
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bootstraping data.....");
        bootstrapService.execute();
    }
}
