package com.book.book_store.entity.categories;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categories")
@Table(name = "categories")
public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoriesNumber;
    private String name;
}
