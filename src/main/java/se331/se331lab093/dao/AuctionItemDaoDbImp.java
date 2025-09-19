package se331.se331lab093.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.se331lab093.entity.AuctionItem;

@Repository
@RequiredArgsConstructor
@Profile("!manual")
public class AuctionItemDaoDbImp implements AuctionItemDao {
    
    private final AuctionItemRepository auctionItemRepository;

    @Override
    public Integer getAuctionItemSize() {
        return Math.toIntExact(auctionItemRepository.count());
    }

    @Override
    public Page<AuctionItem> getAuctionItems(Pageable pageRequest) {
        return auctionItemRepository.findAll(pageRequest);
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String description, Pageable pageRequest) {
        return auctionItemRepository.findByDescriptionContainingIgnoreCase(description, pageRequest);
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String description, String type, Pageable pageRequest) {
        if (description != null && type != null) {
            return auctionItemRepository.findByDescriptionContainingIgnoreCaseAndTypeContainingIgnoreCase(
                    description, type, pageRequest);
        } else if (description != null) {
            return auctionItemRepository.findByDescriptionContainingIgnoreCase(description, pageRequest);
        } else if (type != null) {
            return auctionItemRepository.findByTypeContainingIgnoreCase(type, pageRequest);
        } else {
            return auctionItemRepository.findAll(pageRequest);
        }
    }

    @Override
    public Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double amount, Pageable pageRequest) {
        return auctionItemRepository.findBySuccessfulBidAmountLessThan(amount, pageRequest);
    }
}
