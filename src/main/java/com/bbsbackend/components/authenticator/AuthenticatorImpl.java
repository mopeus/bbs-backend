package com.bbsbackend.components.authenticator;

import java.time.Duration;
import java.time.Instant;

import com.bbsbackend.components.authenticator.common.Attachable;
import com.bbsbackend.components.authenticator.common.Permission;
import com.bbsbackend.components.authenticator.common.ResetToken;
import com.bbsbackend.components.authenticator.common.UserToken;
import com.bbsbackend.components.authenticator.exceptions.AuthenticatorException;
import com.bbsbackend.components.authenticator.exceptions.CommonException;
import com.bbsbackend.components.authenticator.exceptions.ExpireException;
import com.bbsbackend.components.authenticator.exceptions.PasswordInvalid;
import com.bbsbackend.components.authenticator.exceptions.TokenException;
import com.bbsbackend.components.authenticator.exceptions.UsernameInvalid;
import com.bbsbackend.components.authenticator.repository.AuthenticatorRepository;
import com.bbsbackend.components.tokenGenerator.TokenGenerator;
import com.bbsbackend.components.validator.Validator;

public class AuthenticatorImpl implements Authenticator{
    private final String componentName;
    private final AuthenticatorRepository repository;
    private final Validator<String> usernameValidator;
    private final Validator<String> passwordValidator;
    private final TokenGenerator tokenGenerator;
    
    public AuthenticatorImpl(String componentName,
            AuthenticatorRepository repository,
            Validator<String> usernameValidator,
            Validator<String> passwordValidator,
            TokenGenerator tokenGenerator) {
        this.componentName = componentName;
        this.repository = repository;
        this.usernameValidator = usernameValidator;
        this.passwordValidator = passwordValidator;
        this.tokenGenerator = tokenGenerator;
    }
    
    //返回一个匿名函数？？？
	@Override
	public Attachable signUp(String username, String password) throws AuthenticatorException {
		if(!usernameValidator.validate(username))throw new UsernameInvalid("用户名不合法");
	    if (!passwordValidator.validate(password)) throw new PasswordInvalid("密码不合法");
	    if (repository.contains(username)) throw new UsernameInvalid("用户名已存在");

		return permission->{
			if(!repository.saveUser(username, password, permission))throw new CommonException("注册失败");
		};
	}

	@Override
	public UserToken login(String username, String password) throws AuthenticatorException {
	       if (!repository.contains(username)) throw new UsernameInvalid("用户名不存在");
	        if (!repository.getPassword(username).equals(password)) throw new PasswordInvalid("密码错误");

	        Permission permission = repository.getPermissionByUsername(username);
	        String userId = permission.userId;
	        String token = tokenGenerator.genToken(userId, Instant.now().plus(Duration.ofDays(30)));
	        UserToken userToken = UserToken.of(token);

	        if  (!repository.saveUserToken(userToken, permission)) throw new CommonException("登录失败");

	        return userToken;
	}

	
	@Override
	public Permission getLoggedUser(UserToken userToken) throws AuthenticatorException {
		if(tokenGenerator.isExpired(userToken.value)) {
			repository.deleteUserToken(userToken);
			throw new ExpireException("登陆过期");
		}
		Permission permission=repository.getPermissionByToken(userToken);
		 if (permission == null) throw new TokenException("登录信息无效");
		return permission;
	}

	//一用完就丢掉
	@Override
	public void logout(UserToken userToken) {
		tokenGenerator.expire(userToken.value);
		repository.deleteUserToken(userToken);
	}

	@Override
	public ResetToken reqResetPassword(String username) throws AuthenticatorException {
        if (!repository.contains(username)) throw new UsernameInvalid("用户名不存在");

        String token = tokenGenerator.genToken(username, Instant.now().plus(Duration.ofHours(1)));
        ResetToken resetToken = ResetToken.of(token);
        if (!repository.saveResetToken(resetToken, username)) {
            throw new CommonException("请求重设密码失败");
        }
		return resetToken;
	}

	@Override
	public void resetPassword(ResetToken resetToken, String newPassword) throws AuthenticatorException {
		//isExpired已经会将token删除
		if(tokenGenerator.isExpired(resetToken.value)) {
			repository.deleteResetToken(resetToken);
			throw new ExpireException("操作已经过期");
		}
        String username = repository.getUsernameByResetToken(resetToken);
        if (username == null) throw new TokenException("重置信息无效");
        if (!passwordValidator.validate(newPassword)) throw new PasswordInvalid("密码不合法");

        if (!repository.setPassword(username, newPassword)) throw new CommonException("重置无效");
        repository.deleteResetToken(resetToken);
        repository.deleteUserTokenByUsername(username);
	}

	@Override
	public void remove(String username) {
		 repository.deleteUser(username);
	}

	@Override
	public String getName() {
	    return this.componentName;
	}

	@Override
	public boolean isAuthenticated(String username, String password) {
        return repository.contains(username) && repository.getPassword(username).equals(password);
	}

}
