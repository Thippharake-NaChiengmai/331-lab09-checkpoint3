package se331.se331lab093.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.se331lab093.entity.AuctionItem;

public interface AuctionItemDao {
    Integer getAuctionItemSize();

    Page<AuctionItem> getAuctionItems(Pageable pageRequest);

    Page<AuctionItem> getAuctionItems(String description, Pageable pageRequest);

    Page<AuctionItem> getAuctionItems(String description, String type, Pageable pageRequest);

    Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double amount, Pageable pageRequest);
}
