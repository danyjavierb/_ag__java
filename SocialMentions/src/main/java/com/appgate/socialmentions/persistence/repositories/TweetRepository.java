package com.appgate.socialmentions.persistence.repositories;

import com.appgate.socialmentions.persistence.entities.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<TweetEntity, Long> {



}
