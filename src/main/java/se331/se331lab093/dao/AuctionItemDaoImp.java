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
