package com.yiyjm.nest.dao;

import com.yiyjm.nest.entity.Letter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 信 Dao
 *
 * @author jonny
 * @date 2020/04/30
 */
@Mapper
@Repository
public interface LetterDao {
	/**
	 * 列表的信
	 *
	 * @param lid    盖子
	 * @param number 数量
	 * @return {@link List<Letter>}
	 */
	@Select("select * from letter where lid<#{lid} order by lid desc limit #{number}")
	List<Letter> listLetter(@Param("lid") int lid, @Param("number") int number);

	/**
	 * 名单由兰德
	 *
	 * @param number 数量
	 * @return {@link List<Letter>}
	 */
	@Select("SELECT * FROM letter AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(lid)-#{number} FROM letter)) AS lid) AS t2 WHERE t1.lid >= t2.lid ORDER BY t1.lid ASC LIMIT #{number}")
	List<Letter> listByRand(@Param("number") int number);

	/**
	 * 插入的信
	 * <code>@Options(useGeneratedKeys = true, keyProperty = "lid", keyColumn = "lid")<code/>
	 *
	 * @param letter 信
	 * @return int
	 */
	@Insert("insert into letter(nickname,content,ip,pubtime) values(#{nickname},#{content},#{ip},now())")
	int insertLetter(Letter letter);

	/**
	 * 攒的信
	 *
	 * @param lid 盖子
	 * @return int
	 */
	@Update("update letter set zan=zan+1 where lid=#{lid}")
	int zanLetter(@Param("lid") int lid);
}
