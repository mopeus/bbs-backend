package com.bbsbackend.components.userComponent.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bbsbackend.types.Identifier;
import com.bbsbackend.types.MajorValue;
import com.bbsbackend.types.User;

public class UserRepositoryMryImpl implements UserRepository{
	private List<User> users=Stream.of(
			new User("001", "小陈",new Identifier() , true, "18", new MajorValue("北大青鸟", "挖掘机"), "我真帅"),
			new User("002","小文", new Identifier(), true, "16", new MajorValue("北大青鸟", "挖掘机"), "我真帅"),
			new User("003", "小美",new Identifier(), false, "18", new MajorValue("北大青鸟", "挖掘机"), "我真丑"),
			new User("004", "小天",new Identifier(), false, "18", new MajorValue("哈尔滨", "佛学"), "我真美"),
			new User("005", "小强",new Identifier(), true, "15", new MajorValue("哈尔滨", "佛学"), "我真帅"),
			new User("006", "小飞",new Identifier(), true, "17", new MajorValue("北大青鸟", "挖掘机"), "我真牛逼")
			).collect(Collectors.toList());

	
	@Override
	public boolean saveUser(User user) {
		return users.add(user);
	}

	@Override
	public Optional<User> searchUserById(String id) {
		return users.stream().filter(x->x.getId().equals(id)).findFirst();
	}

	
	@Override
	public boolean updateInfo(String id, User newuserInfo) {
		Optional<User>user=users.stream().filter(x->x.getId().equals(id)).findFirst();
		if(user.isPresent()) {
			user.get().update(newuserInfo);
			return true;
		}
		return false;
	}

	
	@Override
	public boolean removeUser(String id) {
		return users.removeIf(x->(x.getId()).equals(id));
	}
	
}
