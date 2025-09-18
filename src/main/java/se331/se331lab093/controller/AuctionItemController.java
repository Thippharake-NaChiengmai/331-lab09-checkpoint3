package se331.se331lab093.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.se331lab093.entity.AuctionItem;
import se331.se331lab093.services.AuctionItemServices;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuctionItemController {
    final AuctionItemServices auctionItemServices;

    @GetMapping("/items")
    public ResponseEntity<?> getAuctionItems(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page,
            @RequestParam(value = "description", required = false) String description) {
        
        perPage = perPage == null ? 10 : perPage;
        page = page == null ? 0 : page;
        Page<AuctionItem> pageOutput;

        if (description != null) {
            pageOutput = auctionItemServices.getAuctionItems(description, PageRequest.of(page, perPage));
        } else {
            pageOutput = auctionItemServices.getAuctionItems(PageRequest.of(page, perPage));
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/items/byBidAmount")
    public ResponseEntity<?> getAuctionItemsBySuccessfulBidAmount(
            @RequestParam(value = "lessThan") Double amount,
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {
        
        perPage = perPage == null ? 10 : perPage;
        page = page == null ? 0 : page;
        
        Page<AuctionItem> pageOutput = auctionItemServices.getAuctionItemsBySuccessfulBidLessThan(
                amount, PageRequest.of(page, perPage));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeaders, HttpStatus.OK);
    }
}
