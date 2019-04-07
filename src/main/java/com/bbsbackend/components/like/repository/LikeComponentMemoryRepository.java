package com.bbsbackend.components.like.repository;

import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Stream;

import com.bbsbackend.components.like.common.LikeId;
import com.bbsbackend.components.like.common.LikeInfo;
import com.google.gson.Gson;
/*
 * 不看了
 */
public class LikeComponentMemoryRepository implements LikeComponentRepository{
	private static final Gson gson = new Gson();
    private final String repositoryName;

    // LikeId -> LikeInfo
    private final Map<String, String> idToInfo = new Hashtable<>();

    private LikeComponentMemoryRepository(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    @Override
    public boolean saveLike(LikeId likeId, LikeInfo likeInfo) {
        if (idToInfo.containsKey(likeId.value)) return false;
        idToInfo.put(likeId.value, gson.toJson(likeInfo));
        return true;
    }

    @Override
    public boolean updateLike(LikeId likeId, LikeInfo likeInfo) {
        if (!idToInfo.containsKey(likeId.value)) return false;
        idToInfo.put(likeId.value, gson.toJson(likeInfo));
        return true;
    }

    @Override
    public LikeInfo getLikeInfo(LikeId id) {
        return gson.fromJson(idToInfo.get(id.value), LikeInfo.class);
    }

    @Override
    public Stream<LikeId> getAllLike() {
        return idToInfo.keySet().stream().map(LikeId::of);
    }

    @Override
    public void removeLike(LikeId id) {
        idToInfo.remove(id.value);
    }

    @Override
    public String getRepositoryName() {
        return this.repositoryName;
    }

    public static LikeComponentRepository get(String repositoryName) {
        return new LikeComponentMemoryRepository(repositoryName);
    }
}
