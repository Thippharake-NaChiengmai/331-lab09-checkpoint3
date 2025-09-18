package se331.se331lab093.entity;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleAuctionItem {
    Long id;
    String description;
    String type;
    
    @Builder.Default
    List<SimpleBid> bids = new ArrayList<>();
    
    SimpleBid successfulBid;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class SimpleBid {
    Long id;
    Double amount;
    String datetime;
}
