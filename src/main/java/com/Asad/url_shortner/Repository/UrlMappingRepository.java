package com.Asad.url_shortner.Repository;

import com.Asad.url_shortner.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {



    Optional<UrlMapping> findByShortCode(String shortcode);
}
