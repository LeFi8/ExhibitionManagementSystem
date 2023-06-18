package com.mas.exhibitionmanagementsystem.models;

import com.mas.exhibitionmanagementsystem.models.employees.Cashier;
import com.mas.exhibitionmanagementsystem.models.enums.TicketDiscount;
import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_discount", nullable = false)
    private TicketDiscount ticketDiscount;

    @ManyToOne()
    @JoinColumn(name = "id_cashier")
    private Cashier cashier;
}
