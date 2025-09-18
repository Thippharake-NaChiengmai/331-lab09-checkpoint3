package se331.se331lab093.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.se331lab093.entity.Bid;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long>{
    List<Bid> findAll();
}
