package com.bbsbackend.components.authenticator.common;

import com.bbsbackend.components.authenticator.exceptions.CommonException;

public interface Attachable {
	void attach(Permission premission)throws CommonException;
	
}
