package ru.gb.thymeleafproject.service.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gb.thymeleafproject.config.JmsConfig;
import ru.gb.thymeleafproject.model.CategoryChangeMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class JmsSenderService {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 5000)
    public void sendAndReceiveMessage(String oldTitle, String newTitle) {
        CategoryChangeMessage message = CategoryChangeMessage.builder()
                .message("Category changed")
                .oldTitle(oldTitle)
                .newTitle(newTitle)
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.GB_QUEUE_RECEIVE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                try {
                    TextMessage textMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    textMessage.setStringProperty("_type", CategoryChangeMessage.class.getCanonicalName());
                    log.warn("Sending message");

                    return textMessage;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new JMSException(e.getMessage());
                }
            }
        });
        try {
            log.error("Response message: {}", receivedMessage.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
