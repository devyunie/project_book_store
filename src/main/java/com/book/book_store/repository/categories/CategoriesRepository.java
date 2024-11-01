package com.book.book_store.repository.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.book_store.entity.categories.CategoriesEntity;

@Repository 
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {
}
