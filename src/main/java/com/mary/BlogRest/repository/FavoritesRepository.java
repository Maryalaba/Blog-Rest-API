package com.mary.BlogRest.repository;

import com.mary.BlogRest.model.Favorites;
import com.mary.BlogRest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    List<Favorites> findAllByUser(User user);
}
