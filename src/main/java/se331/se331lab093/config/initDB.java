package se331.se331lab093.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
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
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Create 5 auction items
        AuctionItem item1 = new AuctionItem();
        item1.setDescription("Vintage Watch");
        item1.setType("Collectibles");

        AuctionItem item2 = new AuctionItem();
        item2.setDescription("Gaming Laptop");
        item2.setType("Electronics");

        AuctionItem item3 = new AuctionItem();
        item3.setDescription("Antique Vase");
        item3.setType("Art");

        AuctionItem item4 = new AuctionItem();
        item4.setDescription("First Edition Book");
        item4.setType("Books");

        AuctionItem item5 = new AuctionItem();
        item5.setDescription("Diamond Ring");
        item5.setType("Jewelry");

        // Save items first
        List<AuctionItem> items = List.of(item1, item2, item3, item4, item5);
        auctionItemRepository.saveAll(items);

        // Create bids for each item
        // Item 1 bids
        Bid bid1_1 = createBid(100.0, item1);
        Bid bid1_2 = createBid(150.0, item1);
        Bid bid1_3 = createBid(200.0, item1);
        item1.setSuccessfulBid(bid1_3);

        // Item 2 bids
        Bid bid2_1 = createBid(800.0, item2);
        Bid bid2_2 = createBid(900.0, item2);
        Bid bid2_3 = createBid(1000.0, item2);
        item2.setSuccessfulBid(bid2_3);

        // Item 3 bids
        Bid bid3_1 = createBid(300.0, item3);
        Bid bid3_2 = createBid(350.0, item3);
        Bid bid3_3 = createBid(400.0, item3);
        item3.setSuccessfulBid(bid3_3);

        // Item 4 bids
        Bid bid4_1 = createBid(50.0, item4);
        Bid bid4_2 = createBid(75.0, item4);
        Bid bid4_3 = createBid(85.0, item4);

        // Item 5 bids
        Bid bid5_1 = createBid(1500.0, item5);
        Bid bid5_2 = createBid(1600.0, item5);
        Bid bid5_3 = createBid(1700.0, item5);

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
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setDatetime(LocalDateTime.now());
        bid.setItem(item);
        return bid;
    }
}
