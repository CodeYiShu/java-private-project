package com.codeshu.condition.service.impl;

import com.codeshu.condition.bean.User;
import com.codeshu.condition.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 如果容器中配置user.datasource为Clickhouse，则此Bean生效，没配置也不生效
 *
 * @author CodeShu
 * @date 2023/3/30 9:06
 */
@Service("userForClickhouseService")
@ConditionalOnProperty(prefix = "user", name = "datasource", havingValue = "Clickhouse", matchIfMissing = false)
public class UserForClickhouseServiceImpl implements UserService {
	@Override
	public User getUser() {
		return new User(2L, "Clickhouse", 12);
	}
}
