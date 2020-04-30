package com.yiyjm.nest.dao;

import com.yiyjm.nest.entity.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客 Dao
 *
 * @author jonny
 * @date 2020/04/30
 */
@Mapper
@Repository
public interface BlogDao {
	/**
	 * 插入
	 *
	 * @param blog 博客
	 * @return int
	 */
	@Options(useGeneratedKeys = true, keyProperty = "bid", keyColumn = "bid")
	@Insert("insert into blog(`rank`,title,keyword,content,modtime,url) " +
			" values(#{rank},#{title},#{keyword},#{content},#{modtime},#{url})")
	int insert(Blog blog);

	/**
	 * 更新
	 *
	 * @param blog 博客
	 * @return int
	 */
	@Update("update blog set `rank`=#{rank},title=#{title},keyword=#{keyword}, " +
			" content=#{content},modtime=#{modtime},url=#{url} where bid=#{bid}")
	int update(Blog blog);

	/**
	 * 查询
	 *
	 * @param bid 报价
	 * @return {@link Blog}
	 */
	@Select("select * from blog where bid=#{bid}")
	Blog get(@Param("bid") int bid);

	/**
	 * 查找最后一个草稿
	 *
	 * @return {@link Integer}
	 */
	@Select("select bid from blog where `rank`<0 order by bid desc limit 1")
	Integer getLastDraft();

	/**
	 * 兰德公司列表
	 *
	 * @param number 数量
	 * @return {@link List<Blog>}
	 */
	@Select("SELECT t1.bid,t1.title FROM blog AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(bid)-#{number} FROM blog)) AS bid) AS t2 WHERE t1.bid >= t2.bid ORDER BY t1.bid ASC LIMIT #{number}")
	List<Blog> listRand(@Param("number") int number);

	/**
	 * 按 key 精确度匹配，key 长度 > 1
	 *
	 * @param key 关键
	 * @param cur 坏蛋
	 * @param per 每
	 * @return {@link List<Blog>}
	 */
	@Select("select bid,`rank`,title,keyword,modtime from blog where " +
			" MATCH (title,keyword) AGAINST (#{key} IN NATURAL LANGUAGE MODE) limit #{cur},#{per}")
	List<Blog> searchByKey2(@Param("key") String key,
							@Param("cur") int cur,
							@Param("per") int per);

	/**
	 * key 为 null 或长度=1时使用。后发表的排在最前
	 *
	 * @param cur 坏蛋
	 * @param per 每
	 * @return {@link List<Blog>}
	 */
	@Select("select bid,`rank`,title,keyword,modtime from blog order by bid desc limit #{cur},#{per}")
	List<Blog> listByPage(@Param("cur") int cur, @Param("per") int per);

	/**
	 * 计算所有
	 *
	 * @return int
	 */
	@Select("select count(*) from blog")
	int countAll();

	/**
	 * 通过url获得报价
	 *
	 * @param url url
	 * @return {@link Integer}
	 */
	@Select("select bid from blog where url = #{url}")
	Integer getBidByUrl(@Param("url") String url);
}
