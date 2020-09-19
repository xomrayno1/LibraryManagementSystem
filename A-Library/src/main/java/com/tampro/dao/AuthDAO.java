package com.tampro.dao;

import com.tampro.entity.Auth;

public interface AuthDAO<E> extends BaseDAO<E> {

	Auth find(int roleId, int menuId);
}
