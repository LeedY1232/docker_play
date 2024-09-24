package org.henry.docker.db.mapper;

import org.henry.docker.db.entity.TUser;

/**
 *  @author henry
 *  @date 2024/5/24 11:47
 */
public interface TUserMapper {
    int insert(TUser record);

    TUser selectByPrimaryKey(Integer id);
}