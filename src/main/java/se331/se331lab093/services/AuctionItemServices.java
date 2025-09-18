package se331.se331lab093.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.se331lab093.entity.AuctionItem;

public interface AuctionItemServices {
    Page<AuctionItem> getAuctionItems(Pageable pageable);
    Page<AuctionItem> getAuctionItems(String description, Pageable pageable);
    Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double amount, Pageable pageable);
}
