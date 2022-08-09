package com.appgate.socialmentions.persistence.repositories;

import com.appgate.socialmentions.persistence.entities.PostEntity;
import com.appgate.socialmentions.persistence.entities.TweetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {



}
