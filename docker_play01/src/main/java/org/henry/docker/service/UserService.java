package org.henry.docker.service;

import javax.annotation.Resource;

import org.henry.docker.db.entity.TUser;
import org.henry.docker.db.mapper.TUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author henry
 * @date 2024/5/24 12:01
 */

@Service
public class UserService {

    public static final String USER_KEY = "USER_";

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void create(TUser user) {
        int i = tUserMapper.insert(user);
        if (i > 0) {
            logger.info("Insert User SuccessFully, id:{}, name:{}", user.getId(), user.getUsername());
            String key = USER_KEY + user.getId();
            redisTemplate.opsForValue().set(key, user);
        } else {
            logger.warn("create failed,user:{}", user);
        }

    }

    public TUser findUser(Integer userId) {
        String key = USER_KEY + userId;
        TUser user = null;
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj instanceof TUser) {
            user = (TUser) obj;
        }
        if (user == null) {
            // 查询数据库
            user = tUserMapper.selectByPrimaryKey(userId);
            if (user != null) {
                redisTemplate.opsForValue().set(key, user);
            }
        }
        return user;
    }
}
