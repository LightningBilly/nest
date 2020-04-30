package com.yiyjm.nest.dao;

import com.yiyjm.nest.entity.Chat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 聊天 Dao
 *
 * @author jonny
 * @date 2020/04/30
 */
@Mapper
@Repository
public interface ChatDao {
	/**
	 * 列表
	 *
	 * @param cid    cid
	 * @param number 数量
	 * @return {@link List<Chat>}
	 */
	@Select("select * from chat where cid<#{cid} order by cid desc limit #{number}")
	List<Chat> list(@Param("cid") int cid, @Param("number") int number);

	/**
	 * 插入消息
	 *
	 * @param message 消息
	 * @return int
	 */
	@Insert("insert into chat(male,content,pubtime) values(#{male},#{content},now())")
	int insertMessage(Chat message);
}
