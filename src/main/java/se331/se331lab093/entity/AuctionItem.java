package se331.se331lab093.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    String type;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @Builder.Default
    List<Bid> bids = new ArrayList<>();

    @OneToOne
    Bid successfulBid;
}

