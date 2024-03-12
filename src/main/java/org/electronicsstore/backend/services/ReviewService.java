package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.customer.Review;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.ReviewRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService implements BaseService<Review, String> {
    private final ReviewRepo reviewRepo;


    @Override
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return reviewRepo.findAllBy(clz);
    }

    @Override
    public Review findById(String id) {
        return reviewRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public <P> P findProjById(String id, Class<P> clz) {
        return reviewRepo.findProjById(id, clz).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public List<Review> findAllById(List<String> ids) {
        return reviewRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<String> ids, Class<P> clz) {
        return reviewRepo.findAllProjByIdIn(ids, clz);
    }

    @Override
    public Review createOne(Review entity) {
        return reviewRepo.save(entity);
    }

    @Override
    public Review updateOne(String id, Review entity) {
        return reviewRepo.save(entity);
    }

    @Override
    public Review patchOne(String id, Review entity) {
        return reviewRepo.save(entity);
    }

    @Override
    public void deleteOne(String id) {
        var review = reviewRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        reviewRepo.delete(review);
    }

    public <P> List<P> findAllProjByCustomerId(String customerId, Class<P> clz) {
        return reviewRepo.findAllProjByCustomerId(customerId, clz);
    }

    public <P> List<P> findAllProjByProductId(String productId, Class<P> clz) {
        return reviewRepo.findAllProjByProductId(productId, clz);
    }
}
