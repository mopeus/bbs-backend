package com.bbsbackend.components.like;

import java.util.Optional;
import java.util.stream.Stream;

import com.bbsbackend.components.idGenerator.IdGenerator;
import com.bbsbackend.components.like.common.LikeId;
import com.bbsbackend.components.like.common.LikeInfo;
import com.bbsbackend.components.like.repository.LikeComponentRepository;

public interface LikeComponent {
    Optional<LikeId> initLike(String obj);

    boolean like(LikeId likee, String liker);

    boolean cancelLike(LikeId likee, String liker);

    boolean dislike(LikeId likee, String disliker);

    Optional<LikeInfo> likeInfo(LikeId likeId);

    Optional<Integer> likeValue(LikeId likeId, LikeCalc calc);

    Stream<LikeId> allLikes();

    void remove(LikeId likeId);

    String getName();

    static LikeComponent defau1t(String componentName, LikeComponentRepository repository, IdGenerator idGenerator) {
        return new LikeComponentImpl(componentName, repository, idGenerator);
    }

    interface LikeCalc {
        int apply(int likerNum, int diskerNum);
    }
}
