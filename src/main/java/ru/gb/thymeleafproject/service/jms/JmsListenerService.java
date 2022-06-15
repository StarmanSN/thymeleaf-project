package ru.gb.thymeleafproject.service.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.gb.thymeleafproject.config.JmsConfig;

@Component
@RequiredArgsConstructor
@Slf4j
public class JmsListenerService {

    private final JmsTemplate jmsTemplate;
    private final MailService mailService;

    @JmsListener(destination = JmsConfig.GB_QUEUE_RECEIVE)
    public void listen() {

    }
}
