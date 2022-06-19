package ru.gb.thymeleafproject.model;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryChangeMessage implements Serializable {

    static final long serialVersionUID = 874953368978183545L;

    private String message;
    private String oldTitle;
    private String newTitle;

    @Override
    public String toString() {
        return "CategoryChangeMessage{" +
                "message='" + message + '\'' +
                ", oldTitle='" + oldTitle + '\'' +
                ", newTitle='" + newTitle + '\'' +
                '}';
    }
}
