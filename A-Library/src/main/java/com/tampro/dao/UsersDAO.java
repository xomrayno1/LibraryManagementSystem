package com.tampro.dao;

import com.tampro.entity.Users;

public interface UsersDAO<E> extends BaseDAO<E> {
	int saveInt(Users users);

}
