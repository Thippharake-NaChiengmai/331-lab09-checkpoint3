package se331.se331lab093.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se331.se331lab093.entity.AuctionItem;

@Repository
public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    Page<AuctionItem> findByDescriptionContaining(String description, Pageable pageable);
    Page<AuctionItem> findBySuccessfulBid_AmountLessThan(Double amount, Pageable pageable);
}
