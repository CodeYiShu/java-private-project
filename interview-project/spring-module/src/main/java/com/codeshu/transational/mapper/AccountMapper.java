package com.codeshu.transational.mapper;

import com.codeshu.transational.entity.AccountEntity;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/6/8 15:01
 */
public interface AccountMapper {
	List<AccountEntity> findAll();

	void update(AccountEntity accountEntity);
}
