package se331.se331lab093.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.se331lab093.entity.AuctionItem;
import se331.se331lab093.entity.Bid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class AuctionItemDaoImp implements AuctionItemDao {
    List<AuctionItem> auctionItems;

    @PostConstruct
    public void init() {
        auctionItems = new ArrayList<>();
        
        // Add sample data for manual profile
        
        // Item 1: Vintage Rolex Watch - High-end collectible
        AuctionItem item1 = new AuctionItem();
        item1.setId(1L);
        item1.setDescription("Vintage Rolex Watch");
        item1.setType("Collectibles");
        List<Bid> bids1 = new ArrayList<>();
        bids1.add(createBid(1L, 5000.0, item1));
        bids1.add(createBid(2L, 7500.0, item1));
        bids1.add(createBid(3L, 8200.0, item1));
        bids1.add(createBid(4L, 9500.0, item1));
        item1.setBids(bids1);
        item1.setSuccessfulBid(bids1.get(3)); // Highest bid wins

        // Item 2: Gaming Laptop - Mid-range electronics
        AuctionItem item2 = new AuctionItem();
        item2.setId(2L);
        item2.setDescription("Gaming Laptop RTX 4090");
        item2.setType("Electronics");
        List<Bid> bids2 = new ArrayList<>();
        bids2.add(createBid(5L, 1200.0, item2));
        bids2.add(createBid(6L, 1450.0, item2));
        bids2.add(createBid(7L, 1600.0, item2));
        bids2.add(createBid(8L, 1850.0, item2));
        bids2.add(createBid(9L, 2100.0, item2));
        item2.setBids(bids2);
        item2.setSuccessfulBid(bids2.get(4)); // Highest bid wins

        // Item 3: Ming Dynasty Vase - Premium art piece
        AuctionItem item3 = new AuctionItem();
        item3.setId(3L);
        item3.setDescription("Ming Dynasty Vase");
        item3.setType("Art");
        List<Bid> bids3 = new ArrayList<>();
        bids3.add(createBid(10L, 15000.0, item3));
        bids3.add(createBid(11L, 18500.0, item3));
        bids3.add(createBid(12L, 22000.0, item3));
        bids3.add(createBid(13L, 25500.0, item3));
        item3.setBids(bids3);
        item3.setSuccessfulBid(bids3.get(3)); // Highest bid wins

        // Item 4: First Edition Harry Potter - Rare book
        AuctionItem item4 = new AuctionItem();
        item4.setId(4L);
        item4.setDescription("First Edition Harry Potter");
        item4.setType("Books");
        List<Bid> bids4 = new ArrayList<>();
        bids4.add(createBid(14L, 150.0, item4));
        bids4.add(createBid(15L, 275.0, item4));
        bids4.add(createBid(16L, 320.0, item4));
        bids4.add(createBid(17L, 450.0, item4));
        bids4.add(createBid(18L, 580.0, item4));
        bids4.add(createBid(19L, 650.0, item4));
        item4.setBids(bids4);
        // No successful bid yet - still active auction

        // Item 5: 5-Carat Diamond Ring - Luxury jewelry
        AuctionItem item5 = new AuctionItem();
        item5.setId(5L);
        item5.setDescription("5-Carat Diamond Ring");
        item5.setType("Jewelry");
        List<Bid> bids5 = new ArrayList<>();
        bids5.add(createBid(20L, 12000.0, item5));
        bids5.add(createBid(21L, 14500.0, item5));
        bids5.add(createBid(22L, 16800.0, item5));
        bids5.add(createBid(23L, 18200.0, item5));
        bids5.add(createBid(24L, 20500.0, item5));
        item5.setBids(bids5);
        item5.setSuccessfulBid(bids5.get(4)); // Highest bid wins

        auctionItems.add(item1);
        auctionItems.add(item2);
        auctionItems.add(item3);
        auctionItems.add(item4);
        auctionItems.add(item5);
    }

    @Override
    public Integer getAuctionItemSize() {
        return auctionItems.size();
    }

    @Override
    public Page<AuctionItem> getAuctionItems(Pageable pageRequest) {
        int totalElements = auctionItems.size();
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), totalElements);

        return new PageImpl<>(
                auctionItems.subList(start, end),
                PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize()),
                totalElements);
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String description, Pageable pageRequest) {
        List<AuctionItem> matchingItems = auctionItems.stream()
                .filter(item -> item.getDescription().contains(description))
                .toList();

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), matchingItems.size());

        return new PageImpl<>(
                matchingItems.subList(start, end),
                pageRequest,
                matchingItems.size());
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String description, String type, Pageable pageRequest) {
        List<AuctionItem> matchingItems = auctionItems.stream()
                .filter(item -> {
                    boolean matchesDescription = description == null || item.getDescription().toLowerCase().contains(description.toLowerCase());
                    boolean matchesType = type == null || item.getType().toLowerCase().contains(type.toLowerCase());
                    return matchesDescription && matchesType;
                })
                .toList();

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), matchingItems.size());

        return new PageImpl<>(
                matchingItems.subList(start, end),
                pageRequest,
                matchingItems.size());
    }

    @Override
    public Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double amount, Pageable pageRequest) {
        List<AuctionItem> matchingItems = auctionItems.stream()
                .filter(item -> item.getSuccessfulBid() != null && 
                        item.getSuccessfulBid().getAmount() < amount)
                .toList();

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), matchingItems.size());

        return new PageImpl<>(
                matchingItems.subList(start, end),
                pageRequest,
                matchingItems.size());
    }

    private Bid createBid(Long id, Double amount, AuctionItem item) {
        Bid bid = new Bid();
        bid.setId(id);
        bid.setAmount(amount);
        bid.setDatetime(LocalDateTime.now().minusDays((long) (Math.random() * 30))); // Random dates within last 30 days
        bid.setItem(item);
        return bid;
    }
}
