package se331.se331lab093.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import se331.se331lab093.entity.AuctionItem;
import se331.se331lab093.entity.Bid;
import se331.se331lab093.repository.AuctionItemRepository;
import se331.se331lab093.repository.BidRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class initDB implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    AuctionItemRepository auctionItemRepository;

    @Autowired 
    BidRepository bidRepository;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        // Create Auction Items
        AuctionItem item1 = AuctionItem.builder()
                .description("Vintage Rolex Watch")
                .type("Collectibles")
                .build();

        AuctionItem item2 = AuctionItem.builder()
                .description("Gaming Laptop RTX 4090")
                .type("Electronics")
                .build();

        AuctionItem item3 = AuctionItem.builder()
                .description("Ming Dynasty Vase")
                .type("Art")
                .build();

        AuctionItem item4 = AuctionItem.builder()
                .description("First Edition Harry Potter")
                .type("Books")
                .build();

        AuctionItem item5 = AuctionItem.builder()
                .description("5-Carat Diamond Ring")
                .type("Jewelry")
                .build();

        // Save items first
        List<AuctionItem> items = List.of(item1, item2, item3, item4, item5);
        auctionItemRepository.saveAll(items);

        // Create bids for item1
        Bid bid1_1 = createBid(100.0, item1);
        Bid bid1_2 = createBid(150.0, item1);
        Bid bid1_3 = createBid(200.0, item1);
        item1.getBids().addAll(List.of(bid1_1, bid1_2, bid1_3));
        item1.setSuccessfulBid(bid1_3);

        // Create bids for item2
        Bid bid2_1 = createBid(800.0, item2);
        Bid bid2_2 = createBid(900.0, item2);
        Bid bid2_3 = createBid(1000.0, item2);
        item2.getBids().addAll(List.of(bid2_1, bid2_2, bid2_3));
        item2.setSuccessfulBid(bid2_3);

        // Create bids for item3
        Bid bid3_1 = createBid(300.0, item3);
        Bid bid3_2 = createBid(350.0, item3);
        Bid bid3_3 = createBid(400.0, item3);
        item3.getBids().addAll(List.of(bid3_1, bid3_2, bid3_3));
        item3.setSuccessfulBid(bid3_3);

        // Create bids for item4
        Bid bid4_1 = createBid(50.0, item4);
        Bid bid4_2 = createBid(75.0, item4);
        Bid bid4_3 = createBid(85.0, item4);
        item4.getBids().addAll(List.of(bid4_1, bid4_2, bid4_3));

        // Create bids for item5
        Bid bid5_1 = createBid(1500.0, item5);
        Bid bid5_2 = createBid(1600.0, item5);
        Bid bid5_3 = createBid(1700.0, item5);
        item5.getBids().addAll(List.of(bid5_1, bid5_2, bid5_3));

        // Save all bids
        List<Bid> bids = List.of(
            bid1_1, bid1_2, bid1_3,
            bid2_1, bid2_2, bid2_3,
            bid3_1, bid3_2, bid3_3,
            bid4_1, bid4_2, bid4_3,
            bid5_1, bid5_2, bid5_3
        );
        bidRepository.saveAll(bids);

        // Update items with successful bids
        auctionItemRepository.saveAll(items);
    }

    private Bid createBid(Double amount, AuctionItem item) {
        return Bid.builder()
                .amount(amount)
                .datetime(LocalDateTime.now())
                .item(item)
                .build();
    }
}

