package com.bbsbackend.components.like;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.bbsbackend.components.idGenerator.IdGenerator;
import com.bbsbackend.components.like.common.LikeId;
import com.bbsbackend.components.like.common.LikeInfo;
import com.bbsbackend.components.like.repository.LikeComponentRepository;

public class LikeComponentImpl implements LikeComponent{
    private final String componentName;
    private final LikeComponentRepository repository;
    private final IdGenerator idGenerator;
    LikeComponentImpl(String componentName, LikeComponentRepository repository, IdGenerator idGenerator) {
        this.componentName = componentName;
        this.repository = repository;
        this.idGenerator = idGenerator;
    }
	@Override
	public Optional<LikeId> initLike(String obj) {
		LikeId id=LikeId.of(idGenerator.generateId());
		LikeInfo info=LikeInfo.of(obj);
		if(repository.saveLike(id, info))return Optional.of(id);
		return Optional.empty();
	}

	//有必要更新这里的List嘛,是测试能否成功添加,而且作为参数去更新repository
	@Override
	public boolean like(LikeId likee, String liker) {
		Optional<LikeInfo> info=Optional.of(repository.getLikeInfo(likee));
		return info.map(i->i.liker.add(liker)&&repository.updateLike(likee, i)).orElse(false);
	}

	@Override
	public boolean cancelLike(LikeId likee, String liker) {
		Optional<LikeInfo>info=Optional.of(repository.getLikeInfo(likee));
		return info.map(i->i.liker.remove(liker)&&repository.updateLike(likee, i)).orElse(false);
	}

	@Override
	public boolean dislike(LikeId likee, String disliker) {
		Optional<LikeInfo>info=Optional.of(repository.getLikeInfo(likee));
		return info.map(i->i.disliker.add(disliker)&&repository.updateLike(likee, i)).orElse(false);
	}

	//这里重新构造一个深复制
	@Override
	public Optional<LikeInfo> likeInfo(LikeId likeId) {
		Optional<LikeInfo>info=Optional.ofNullable(repository.getLikeInfo(likeId));
		return info.map(i->{
			List<String>disliker=i.disliker;
			List<String>liker=i.liker;
			return LikeInfo.of(i.obj,new ArrayList<>(liker),new ArrayList<>(disliker));
		});
	}

	//LikeCale是需要传递一个匿名函数 是计算点赞数的一个算法
	@Override
	public Optional<Integer> likeValue(LikeId likeId, LikeCalc calc) {
		return Optional.ofNullable(repository.getLikeInfo(likeId)).map(i->calc.apply(i.liker.size(), i.disliker.size()));
	}

	@Override
	public Stream<LikeId> allLikes() {
		return repository.getAllLike();
	}

	@Override
	public void remove(LikeId likeId) {
		repository.removeLike(likeId);
	}
	@Override
	public String getName() {
		return this.componentName;
	}

}
