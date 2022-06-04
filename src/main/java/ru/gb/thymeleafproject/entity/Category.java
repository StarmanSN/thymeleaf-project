package ru.gb.thymeleafproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.gb.thymeleafproject.entity.common.InfoEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
@EntityListeners(AuditingEntityListener.class)
public class Category extends InfoEntity {

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", name='" + title + '\'' +
                '}';
    }

    @Builder
    public Category(Long id, int version, String createdBy, LocalDateTime createdDate, String lastModifiedBy,
                    LocalDateTime lastModifiedDate, String title, Set<Product> products) {
        super(id, version, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.title = title;
        this.products = products;
    }
}
