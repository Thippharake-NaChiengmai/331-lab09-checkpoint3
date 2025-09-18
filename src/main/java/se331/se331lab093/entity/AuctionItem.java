package se331.se331lab093.entity;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class AuctionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;
    String type;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<Bid> bids;

    @OneToOne
    Bid successfulBid;
}
