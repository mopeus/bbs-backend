package com.bbsbackend.components.userComponent;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bbsbackend.components.userComponent.repository.UserRepository;
import com.bbsbackend.components.userComponent.repository.UserRepositoryMryImpl;
import com.bbsbackend.types.Identifier;
import com.bbsbackend.types.MajorValue;
import com.bbsbackend.types.User;
import com.bbsbackend.types.UserInfo;

public class UserComponent {
	private UserRepository userRepository;
	
	public UserComponent(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	//一个save
	public String CreateUser(String username,Identifier portraitIdentifier,boolean gender,String grade,MajorValue majorValue,String introduction) {
		User user=new User(username,portraitIdentifier, gender, grade, majorValue, introduction);
		UUID id=UUID.randomUUID();
		user.setId(id.toString());
		if(userRepository.saveUser(user)) {
			return id.toString();
		}
		return null;
	}
	
	//search 返回值改成info
	public UserInfo GetUserInfo(String id){
		Optional<User> useroptional=userRepository.searchUserById(id);
		if(useroptional.isPresent()) {
		User user=useroptional.get();
		UserInfo userInfo=new UserInfo(user.getName(), user.getPortraitIdentifier(), user.getGender(), user.getGrade(), user.getMajorValue(), user.getIntroduction());
		return userInfo;
		}
		return null;
	}
	
	//update
	public boolean ChangeUserName(String id,String 	newusername) {
		Optional<User> user=userRepository.searchUserById(id);
		if(user.isPresent()) {
			user.get().setName(newusername);
			return userRepository.updateInfo(id, user.get());
		}
			return false;
	}
	
	public boolean ChangePortraitImage(String id,Identifier newportraitimage) {
		Optional<User> user=userRepository.searchUserById(id);
		if(user.isPresent()) {
			user.get().setPortraitIdentifier(newportraitimage);
			return userRepository.updateInfo(id, user.get());
		}
			return false;

	}
	
	public boolean ChangeGender(String id,boolean newgender) {
	Optional<User>user=userRepository.searchUserById(id);
	if(user.isPresent()) {
		user.get().setGender(newgender);
		return userRepository.updateInfo(id, user.get());
	}
		return false;
	}
	
	public boolean ChangeMajor(String id,MajorValue majorValue) {
		Optional<User> user=userRepository.searchUserById(id);
		if(user.isPresent()) {
			user.get().setMajorValue(majorValue);;
			return userRepository.updateInfo(id, user.get());
		}
			return false;

	}
	
	public boolean ChangeIntro(String id,String introduction) {
		Optional<User> user=userRepository.searchUserById(id);
		if(user.isPresent()) {
			user.get().setIntroduction(introduction);;
			return userRepository.updateInfo(id, user.get());
		}
			return false;

	}
	
	
	//删除
	
	public void RemoveUser(String id) {
		userRepository.removeUser(id);
	}
	
	public static void main(String[]args) {
		UserComponent userComponent=new UserComponent(new UserRepositoryMryImpl());
		userComponent.ChangeGender("002", true);
		userComponent.ChangeIntro("002", "美滋滋");
		userComponent.ChangeMajor("002", new MajorValue("华工", "软件"));
		userComponent.ChangeUserName("002", "小奥");
		System.out.println(userComponent.CreateUser("小溪",new Identifier(), false, "50",new MajorValue("中大", "专业2"), "介绍3"));
		UserInfo info=userComponent.GetUserInfo("002");
		System.out.println(info.getGrade()+info.getIntroduction()+info.getName()+info.isGender());
		
	}
}
