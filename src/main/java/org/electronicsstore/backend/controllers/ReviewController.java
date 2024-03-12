package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.customer.ReviewDto;
import org.electronicsstore.backend.dtos.customer.ReviewProj;
import org.electronicsstore.backend.model.customer.Review;
import org.electronicsstore.backend.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ReviewController extends AbstractController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewProj> reviews() {
        return reviewService.findAllBy(ReviewProj.class);
    }

    @GetMapping(value = {"{reviewId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReviewProj> reviewIdId(
            @PathVariable(name = "reviewId", required = true) String reviewId
    ) {
        return ResponseEntity.ok(reviewService.findProjById(reviewId, ReviewProj.class));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createReview(
            HttpServletRequest req,
            @RequestBody ReviewDto dto
    ) {
        var review = reviewService.createOne(modelMapper.map(dto, Review.class));
        return ResponseEntity.created(buildURI(req, review.getId())).build();
    }

    @PutMapping(
            value = {"{promoId}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> putReview(
            @PathVariable(name = "reviewId", required = true) String reviewId,
            @RequestBody ReviewDto dto) {
        var review = reviewService.updateOne(reviewId, modelMapper.map(dto, Review.class));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(
            value = {"{reviewId}"},
            consumes = "application/merge-patch+json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> patchReview(
            @PathVariable(name = "reviewId", required = true) String reviewId,
            @RequestBody JsonNode dto
    ) {
        var fetched = reviewService.findById(reviewId);
        fetched = reviewService.patchOne(reviewId, mergePatch(fetched, Review.class, dto));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"{reviewId}"})
    public ResponseEntity<?> deleteReview(
            @PathVariable(name = "reviewId", required = true) String reviewId
    ) {
        reviewService.deleteOne(reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = {"customer/{customerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReviewProj>> reviewByCustomerId(
            @PathVariable(name = "customerId", required = true) String customerId
    ) {
        return ResponseEntity.ok(reviewService.findAllProjByCustomerId(customerId, ReviewProj.class));
    }

    @GetMapping(value = {"product/{productId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReviewProj>> reviewByProductId(
            @PathVariable(name = "productId", required = true) String productId
    ) {
        return ResponseEntity.ok(reviewService.findAllProjByProductId(productId, ReviewProj.class));
    }
}
