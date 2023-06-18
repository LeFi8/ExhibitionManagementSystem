package com.mas.exhibitionmanagementsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends Person {
}
