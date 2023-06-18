package com.mas.exhibitionmanagementsystem.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client extends Person {

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToOne()
    @JoinColumn(name = "id_account")
    private UserAccount account;

    @OneToOne()
    @JoinColumn(name = "id_pass")
    private Pass pass;
}
