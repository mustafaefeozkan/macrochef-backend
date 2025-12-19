package com.macrochef.repository;

import com.macrochef.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // 1. Kullanıcının KENDİ favorilediklerini bulmak için (İleride Favorites sayfası için lazım olacak)
    // List<Favorite> findByUserId(Long userId);

    // 2. Kullanıcının TARİFLERİNE gelen toplam beğeni sayısını hesapla
    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.recipe.user.id = :userId")
    int countFavoritesReceived(@Param("userId") Long userId);
}