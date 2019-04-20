package com.bsm.oa.user.dao;

import com.bsm.oa.common.constant.Privilege;
import com.bsm.oa.common.model.TeamId;
import com.bsm.oa.common.model.User;
import com.bsm.oa.common.model.UserId;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

  void insertUser(User user);

  boolean userExists(@Param("userId") UserId userId);

  Set<Privilege> getPrivileges(@Param("userId") UserId userId);

  void addPrivileges(@Param("userId") UserId userId,
                     @Param("privileges") Set<Privilege> privileges);

  void clearPrivileges(@Param("userId") UserId userId);

  List<User> getTeammates(@Param("userId") UserId userId);

  boolean teamExists(TeamId teamId);
}
