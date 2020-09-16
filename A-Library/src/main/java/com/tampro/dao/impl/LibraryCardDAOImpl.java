package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.LibraryCardDAO;
import com.tampro.entity.LibraryCard;

@Repository
@Transactional(rollbackFor = Exception.class)
public class LibraryCardDAOImpl  extends BaseDAOImpl<LibraryCard> implements  LibraryCardDAO<LibraryCard>{

}
