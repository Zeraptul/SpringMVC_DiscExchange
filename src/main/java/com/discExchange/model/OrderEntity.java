package com.discExchange.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Orders")

@Getter
@Setter
public class OrderEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderLineEntity> orderLines;

}
