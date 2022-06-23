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
import ru.gb.thymeleafproject.model.CostChangeMessage;

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
    public void sendAndReceiveMessage(String oldPrice, String newPrice) {
        CostChangeMessage message = CostChangeMessage.builder()
                .message("Cost changed")
                .oldCost(oldPrice)
                .newCost(newPrice)
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.GB_QUEUE_RECEIVE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                try {
                    TextMessage textMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    textMessage.setStringProperty("_type", CostChangeMessage.class.getCanonicalName());
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
