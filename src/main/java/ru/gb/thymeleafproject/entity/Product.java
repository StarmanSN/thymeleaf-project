package ru.gb.thymeleafproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.gb.thymeleafproject.entity.common.InfoEntity;
import ru.gb.thymeleafproject.entity.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table (name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product extends InfoEntity {

    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> carts;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", manufactureDate=" + manufactureDate +
//                ", manufacturer=" + manufacturer.getName() +
                "}\n";
    }

    @Builder
    public Product(Long id, int version, String createdBy, LocalDateTime createdDate, String lastModifiedBy,
                   LocalDateTime lastModifiedDate, String title, BigDecimal cost, LocalDate manufactureDate,
                   Manufacturer manufacturer, Status status, Set<Category> carts) {
        super(id, version, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.title = title;
        this.cost = cost;
        this.manufactureDate = manufactureDate;
        this.manufacturer = manufacturer;
        this.status = status;
        this.carts = carts;
    }
}
