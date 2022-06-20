package ru.gb.thymeleafproject.model;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostChangeMessage implements Serializable {

    static final long serialVersionUID = 874953368978183545L;

    private String message;
    private String oldCost;
    private String newCost;

    @Override
    public String toString() {
        return "CostChangeMessage{" +
                "message='" + message + '\'' +
                ", oldCost='" + oldCost + '\'' +
                ", newCost='" + newCost + '\'' +
                '}';
    }
}
