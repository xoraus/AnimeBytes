package io.xoraus.CyberScribeHub.repositories;

import io.xoraus.CyberScribeHub.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
