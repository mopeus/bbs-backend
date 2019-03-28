package com.bbsbackend.components.userComponent.repository;

import java.util.Optional;

import com.bbsbackend.types.Identifier;
import com.bbsbackend.types.MajorValue;
import com.bbsbackend.types.User;

public interface UserRepository {
	public boolean saveUser(User user);
	public Optional<User> searchUserById(String id);
//	public boolean updateUserName(String id,String username);
//	public boolean updatePortraitImage(String id,Identifier portraitIdentifier);
//	public boolean updateChangeGender(String id,boolean newGender);
//	public boolean updateMajor(String id,MajorValue majorValue);
//	public boolean updateIntro(String id,String introduction);
	public boolean updateInfo(String id,User newuserInfo);
	public boolean removeUser(String id);
}
