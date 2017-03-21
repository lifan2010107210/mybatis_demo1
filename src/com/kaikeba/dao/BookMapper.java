package com.kaikeba.dao;

import java.util.List;

import com.kaikeba.entity.Book;

public interface BookMapper {

	/**
	 * 高级搜索
	 */
	List<Book> findBookByParam(Book param);
}
