package se331.se331lab093.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se331.se331lab093.entity.AuctionItem;

@Repository
public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    
    // Find by description containing (case-insensitive)
    Page<AuctionItem> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
    
    // Find by type containing (case-insensitive)
    Page<AuctionItem> findByTypeContainingIgnoreCase(String type, Pageable pageable);
    
    // Find by both description and type containing (case-insensitive)
    Page<AuctionItem> findByDescriptionContainingIgnoreCaseAndTypeContainingIgnoreCase(
            String description, String type, Pageable pageable);
    
    // Find items with successful bid amount less than specified amount
    @Query("SELECT a FROM AuctionItem a WHERE a.successfulBid.amount < :amount")
    Page<AuctionItem> findBySuccessfulBidAmountLessThan(@Param("amount") Double amount, Pageable pageable);
}
