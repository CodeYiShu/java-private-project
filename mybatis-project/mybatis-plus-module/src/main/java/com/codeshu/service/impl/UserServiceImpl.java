package com.codeshu.service.impl;

import com.codeshu.common.MyBaseServiceImpl;
import com.codeshu.dao.UserDao;
import com.codeshu.entity.User;
import com.codeshu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service 实现层去继承 MyBaseServiceImpl 接口，指定 Dao 和实体类
 *
 * @author CodeShu
 * @date 2023/10/30 13:51
 */
@Service
public class UserServiceImpl extends MyBaseServiceImpl<UserDao, User> implements UserService {
}
