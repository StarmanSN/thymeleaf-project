package ru.gb.thymeleafproject.service.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.gb.thymeleafproject.config.JmsConfig;
import ru.gb.thymeleafproject.model.CategoryChangeMessage;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
@RequiredArgsConstructor
@Slf4j
public class JmsListenerService {

    private final JmsTemplate jmsTemplate;
    private final MailService mailService;

    @JmsListener(destination = JmsConfig.GB_QUEUE_RECEIVE)
    public void listen(@Payload CategoryChangeMessage categoryChangeMessage,
                       @Headers MessageHeaders messageHeaders, Message message) {
        CategoryChangeMessage responseMessage = CategoryChangeMessage.builder()
                .message("Response from listener")
                .build();
        log.info("Message {} have been gotten", categoryChangeMessage);
        try {
            jmsTemplate.convertAndSend(message.getJMSReplyTo(), responseMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
