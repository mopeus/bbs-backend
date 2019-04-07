package com.bbsbackend.components.like.repository;

import java.util.stream.Stream;

import com.bbsbackend.components.like.common.LikeId;
import com.bbsbackend.components.like.common.LikeInfo;

public interface LikeComponentRepository {
    boolean saveLike(LikeId likeId, LikeInfo likeInfo);

    boolean updateLike(LikeId likeId, LikeInfo likeInfo);

    LikeInfo getLikeInfo(LikeId id);

    Stream<LikeId> getAllLike();

    void removeLike(LikeId id);

    String getRepositoryName();
}
