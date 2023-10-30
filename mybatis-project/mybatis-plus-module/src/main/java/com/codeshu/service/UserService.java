package com.codeshu.service;

import com.codeshu.common.MyIBaseService;
import com.codeshu.entity.User;

/**
 * Service 接口层去继承 MyIBaseService 接口，指定实体类
 *
 * @author CodeShu
 * @date 2023/10/30 13:50
 */
public interface UserService extends MyIBaseService<User> {
}
