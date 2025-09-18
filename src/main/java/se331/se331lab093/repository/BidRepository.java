package se331.se331lab093.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se331.se331lab093.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Long>{
}
