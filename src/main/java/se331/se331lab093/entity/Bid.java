package se331.se331lab093.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double amount;
    LocalDateTime datetime;

    @ManyToOne
    AuctionItem item;}
