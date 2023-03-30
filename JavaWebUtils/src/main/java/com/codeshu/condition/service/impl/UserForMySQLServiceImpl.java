package com.codeshu.condition.service.impl;

import com.codeshu.condition.bean.User;
import com.codeshu.condition.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 如果容器中配置user.datasource为mysql，则此Bean生效，没配置也默认此Bean生效
 *
 * @author CodeShu
 * @date 2023/3/30 9:06
 */
@Service("userForMySQLService")
@ConditionalOnProperty(prefix = "user", name = "datasource", havingValue = "mysql", matchIfMissing = true)
public class UserForMySQLServiceImpl implements UserService {
	@Override
	public User getUser() {
		return new User(1L, "MySQL", 12);
	}
}
