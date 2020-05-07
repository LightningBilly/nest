package com.yiyjm.nest.dao;

import com.yiyjm.nest.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 用户 Dao
 *
 * @author Jonny.Chang
 * @date 2020/05/06
 */
@Mapper
@Repository
public interface UserDao {

	/**
	 * 插入用户
	 *
	 * @param user 用户
	 * @return int
	 */
	@Options(useGeneratedKeys = true, keyProperty = "uid", keyColumn = "uid")
	@Insert("INSERT INTO user(name, passwd, email, phone_number nick_name, sex, auth, create_time, update_time) VALUES (#{name}, #{passwd}, #{email}, #{phoneNumber}, #{nickName}, #{sex}, #{auth}, new Date(), new Date())")
	int insertUser(User user);

	/**
	 * 更新用户
	 *
	 * @param user 用户
	 * @return int
	 */
	@Update("UPDATE user set name=#{name}, passwd=#{passwd}, email=#{email}, phone_number=#{phoneNumber}, nick_name=#{nickName}, sex=#{sex}, auth=#{auth}, update_time=new Date() WHERE uid=#{uid}")
	int updateUser(User user);

	/**
	 * 按uid查询用户
	 *
	 * @param uid uid
	 * @return int
	 */
	@Select("select * from user where uid=#{uid}")
	User queryUserByUid(@Param("uid") int uid);

	/**
	 * 按名称查询
	 *
	 * @param name 的名字
	 * @return int
	 */
	@Select("select * from user where name=#{name}")
	User queryByName(@Param("name") String name);

	/**
	 * 通过uid删除用户
	 *
	 * @param uid uid
	 * @return int
	 */
	@Delete("DELETE FROM user WHERE uid=#{uid};")
	int deleteUserByUid(@Param("uid") int uid);

	/**
	 * 计算所有用户
	 *
	 * @return int
	 */
	@Select("select count(*) from user")
	int countAllUser();

}
