package se331.se331lab093.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.se331lab093.entity.AuctionItem;

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
        AuctionItem item1 = new AuctionItem();
        item1.setId(1L);
        item1.setDescription("Vintage Rolex Watch");
        item1.setType("Collectibles");
        item1.setBids(new ArrayList<>());

        AuctionItem item2 = new AuctionItem();
        item2.setId(2L);
        item2.setDescription("Gaming Laptop RTX 4090");
        item2.setType("Electronics");
        item2.setBids(new ArrayList<>());

        AuctionItem item3 = new AuctionItem();
        item3.setId(3L);
        item3.setDescription("Ming Dynasty Vase");
        item3.setType("Art");
        item3.setBids(new ArrayList<>());

        AuctionItem item4 = new AuctionItem();
        item4.setId(4L);
        item4.setDescription("First Edition Harry Potter");
        item4.setType("Books");
        item4.setBids(new ArrayList<>());

        AuctionItem item5 = new AuctionItem();
        item5.setId(5L);
        item5.setDescription("5-Carat Diamond Ring");
        item5.setType("Jewelry");
        item5.setBids(new ArrayList<>());

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
}
