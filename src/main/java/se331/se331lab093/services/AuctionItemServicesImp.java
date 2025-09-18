package se331.se331lab093.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.se331lab093.dao.AuctionItemDao;
import se331.se331lab093.entity.AuctionItem;

@Service
@RequiredArgsConstructor
public class AuctionItemServicesImp implements AuctionItemServices {
    final AuctionItemDao auctionItemDao;

    @Override
    public Page<AuctionItem> getAuctionItems(Pageable pageable) {
        return auctionItemDao.getAuctionItems(pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String description, Pageable pageable) {
        return auctionItemDao.getAuctionItems(description, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String description, String type, Pageable pageable) {
        return auctionItemDao.getAuctionItems(description, type, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double amount, Pageable pageable) {
        return auctionItemDao.getAuctionItemsBySuccessfulBidLessThan(amount, pageable);
    }
}
