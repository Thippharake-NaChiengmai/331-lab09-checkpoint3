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

@Component
public class initDB implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    AuctionItemRepository auctionItemRepository;

    @Autowired 
    BidRepository bidRepository;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        // Initialize Auction Items
        AuctionItem item1 = auctionItemRepository.save(AuctionItem.builder()
                .description("Vintage Rolex Watch")
                .type("Collectibles")
                .build());

        AuctionItem item2 = auctionItemRepository.save(AuctionItem.builder()
                .description("Gaming Laptop RTX 4090")
                .type("Electronics")
                .build());

        AuctionItem item3 = auctionItemRepository.save(AuctionItem.builder()
                .description("Ming Dynasty Vase")
                .type("Art")
                .build());

        AuctionItem item4 = auctionItemRepository.save(AuctionItem.builder()
                .description("First Edition Harry Potter")
                .type("Books")
                .build());

        AuctionItem item5 = auctionItemRepository.save(AuctionItem.builder()
                .description("5-Carat Diamond Ring")
                .type("Jewelry")
                .build());

        // Initialize Bids for item1
        bidRepository.save(Bid.builder()
                .amount(100.0)
                .datetime(LocalDateTime.now())
                .item(item1)
                .build());

        bidRepository.save(Bid.builder()
                .amount(150.0)
                .datetime(LocalDateTime.now())
                .item(item1)
                .build());

        Bid successfulBid1 = bidRepository.save(Bid.builder()
                .amount(200.0)
                .datetime(LocalDateTime.now())
                .item(item1)
                .build());

        // Initialize Bids for item2
        bidRepository.save(Bid.builder()
                .amount(800.0)
                .datetime(LocalDateTime.now())
                .item(item2)
                .build());

        bidRepository.save(Bid.builder()
                .amount(900.0)
                .datetime(LocalDateTime.now())
                .item(item2)
                .build());

        Bid successfulBid2 = bidRepository.save(Bid.builder()
                .amount(1000.0)
                .datetime(LocalDateTime.now())
                .item(item2)
                .build());

        // Initialize Bids for item3
        bidRepository.save(Bid.builder()
                .amount(300.0)
                .datetime(LocalDateTime.now())
                .item(item3)
                .build());

        bidRepository.save(Bid.builder()
                .amount(350.0)
                .datetime(LocalDateTime.now())
                .item(item3)
                .build());

        Bid successfulBid3 = bidRepository.save(Bid.builder()
                .amount(400.0)
                .datetime(LocalDateTime.now())
                .item(item3)
                .build());

        // Initialize Bids for item4
        bidRepository.save(Bid.builder()
                .amount(50.0)
                .datetime(LocalDateTime.now())
                .item(item4)
                .build());

        bidRepository.save(Bid.builder()
                .amount(75.0)
                .datetime(LocalDateTime.now())
                .item(item4)
                .build());

        bidRepository.save(Bid.builder()
                .amount(85.0)
                .datetime(LocalDateTime.now())
                .item(item4)
                .build());

        // Initialize Bids for item5
        bidRepository.save(Bid.builder()
                .amount(1500.0)
                .datetime(LocalDateTime.now())
                .item(item5)
                .build());

        bidRepository.save(Bid.builder()
                .amount(1600.0)
                .datetime(LocalDateTime.now())
                .item(item5)
                .build());

        bidRepository.save(Bid.builder()
                .amount(1700.0)
                .datetime(LocalDateTime.now())
                .item(item5)
                .build());

        // Update items with successful bids
        item1.setSuccessfulBid(successfulBid1);
        item2.setSuccessfulBid(successfulBid2);
        item3.setSuccessfulBid(successfulBid3);

        auctionItemRepository.save(item1);
        auctionItemRepository.save(item2);
        auctionItemRepository.save(item3);
    }

}

