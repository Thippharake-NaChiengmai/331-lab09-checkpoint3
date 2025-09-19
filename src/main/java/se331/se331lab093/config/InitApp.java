package se331.se331lab093.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se331.se331lab093.dao.AuctionItemRepository;
import se331.se331lab093.entity.AuctionItem;
import se331.se331lab093.entity.Bid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!manual")
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    
    private final AuctionItemRepository auctionItemRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // Check if data already exists
        if (auctionItemRepository.count() > 0) {
            return;
        }

        // Initialize sample data
        initializeAuctionItems();
    }

    private void initializeAuctionItems() {
        List<AuctionItem> items = new ArrayList<>();

        // Item 1: Vintage Rolex Watch - High-end collectible
        AuctionItem item1 = AuctionItem.builder()
                .description("Vintage Rolex Watch")
                .type("Collectibles")
                .build();
        
        List<Bid> bids1 = new ArrayList<>();
        bids1.add(createBid(5000.0, item1, 25));
        bids1.add(createBid(7500.0, item1, 20));
        bids1.add(createBid(8200.0, item1, 15));
        bids1.add(createBid(9500.0, item1, 10));
        
        item1.setBids(bids1);
        item1.setSuccessfulBid(bids1.get(3)); // Highest bid wins
        items.add(item1);

        // Item 2: Gaming Laptop - Mid-range electronics
        AuctionItem item2 = AuctionItem.builder()
                .description("Gaming Laptop RTX 4090")
                .type("Electronics")
                .build();
        
        List<Bid> bids2 = new ArrayList<>();
        bids2.add(createBid(1200.0, item2, 30));
        bids2.add(createBid(1450.0, item2, 25));
        bids2.add(createBid(1600.0, item2, 20));
        bids2.add(createBid(1850.0, item2, 15));
        bids2.add(createBid(2100.0, item2, 10));
        
        item2.setBids(bids2);
        item2.setSuccessfulBid(bids2.get(4)); // Highest bid wins
        items.add(item2);

        // Item 3: Ming Dynasty Vase - Premium art piece
        AuctionItem item3 = AuctionItem.builder()
                .description("Ming Dynasty Vase")
                .type("Art")
                .build();
        
        List<Bid> bids3 = new ArrayList<>();
        bids3.add(createBid(15000.0, item3, 28));
        bids3.add(createBid(18500.0, item3, 22));
        bids3.add(createBid(22000.0, item3, 18));
        bids3.add(createBid(25500.0, item3, 12));
        
        item3.setBids(bids3);
        item3.setSuccessfulBid(bids3.get(3)); // Highest bid wins
        items.add(item3);

        // Item 4: First Edition Harry Potter - Rare book
        AuctionItem item4 = AuctionItem.builder()
                .description("First Edition Harry Potter")
                .type("Books")
                .build();
        
        List<Bid> bids4 = new ArrayList<>();
        bids4.add(createBid(150.0, item4, 35));
        bids4.add(createBid(275.0, item4, 30));
        bids4.add(createBid(320.0, item4, 25));
        bids4.add(createBid(450.0, item4, 20));
        bids4.add(createBid(580.0, item4, 15));
        bids4.add(createBid(650.0, item4, 10));
        
        item4.setBids(bids4);
        // No successful bid yet - still active auction
        items.add(item4);

        // Item 5: 5-Carat Diamond Ring - Luxury jewelry
        AuctionItem item5 = AuctionItem.builder()
                .description("5-Carat Diamond Ring")
                .type("Jewelry")
                .build();
        
        List<Bid> bids5 = new ArrayList<>();
        bids5.add(createBid(12000.0, item5, 26));
        bids5.add(createBid(14500.0, item5, 22));
        bids5.add(createBid(16800.0, item5, 18));
        bids5.add(createBid(18200.0, item5, 14));
        bids5.add(createBid(20500.0, item5, 10));
        
        item5.setBids(bids5);
        item5.setSuccessfulBid(bids5.get(4)); // Highest bid wins
        items.add(item5);

        // Item 6: Smartphone iPhone 15 Pro - Electronics
        AuctionItem item6 = AuctionItem.builder()
                .description("iPhone 15 Pro Max")
                .type("Electronics")
                .build();
        
        List<Bid> bids6 = new ArrayList<>();
        bids6.add(createBid(800.0, item6, 20));
        bids6.add(createBid(950.0, item6, 15));
        bids6.add(createBid(1100.0, item6, 10));
        
        item6.setBids(bids6);
        // No successful bid yet - still active auction
        items.add(item6);

        // Item 7: Antique Pocket Watch - Collectibles
        AuctionItem item7 = AuctionItem.builder()
                .description("Antique Gold Pocket Watch")
                .type("Collectibles")
                .build();
        
        List<Bid> bids7 = new ArrayList<>();
        bids7.add(createBid(300.0, item7, 18));
        bids7.add(createBid(425.0, item7, 12));
        bids7.add(createBid(550.0, item7, 8));
        
        item7.setBids(bids7);
        item7.setSuccessfulBid(bids7.get(2)); // Highest bid wins
        items.add(item7);

        // Save all items
        auctionItemRepository.saveAll(items);
        
        System.out.println("✅ Database initialized with " + items.size() + " auction items");
    }

    private Bid createBid(Double amount, AuctionItem item, int daysAgo) {
        return Bid.builder()
                .amount(amount)
                .datetime(LocalDateTime.now().minusDays(daysAgo))
                .item(item)
                .build();
    }
}
