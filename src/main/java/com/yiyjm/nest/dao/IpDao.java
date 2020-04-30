package com.yiyjm.nest.dao;

import com.yiyjm.nest.entity.Chart;
import com.yiyjm.nest.entity.Ip;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Ip Dao
 *
 * @author jonny
 * @date 2020/04/30
 */
@Mapper
@Repository
public interface IpDao {
	/**
	 * ip列表
	 *
	 * @param current 当前的
	 * @param per     每
	 * @return {@link List<Ip>}
	 */
	@Select("select * from ip order by iid desc limit #{current},#{per}")
	List<Ip> listIp(@Param("current") int current, @Param("per") int per);

	/**
	 * 汇总
	 *
	 * @return int
	 */
	@Select("select count(*) as total from ip")
	int total();

	/**
	 * 插入ip
	 *
	 * @param ip 知识产权
	 * @return int
	 */
	@Insert("insert into ip(ip,region,city,isp,curtime) values(#{ip},#{region},#{city},#{isp},now())")
	int insertIp(Ip ip);


	/**
	 * 前 5 城市
	 *
	 * @return {@link List<Chart>}
	 */
	@Select("select city as name,count(*) as num from ip group by city order by num desc limit 5")
	List<Chart> listTop5City();

	/**
	 * 前 5 运营商
	 *
	 * @return {@link List<Chart>}
	 */
	@Select("select isp as name,count(*) as num from ip group by isp order by num desc limit 5")
	List<Chart> listTop5Isp();

	/**
	 * 最后 5 月
	 *
	 * @param time 时间
	 * @return {@link List<Chart>}
	 */
	@Select("select DATE_FORMAT(curtime,'%Y-%m') as name,count(*) as num from ip where curtime>#{time} group by name order by name desc limit 5")
	List<Chart> listMonth5(@Param("time") Timestamp time);

	/**
	 * 最后 5 天
	 *
	 * @param time 时间
	 * @return {@link List<Chart>}
	 */
	@Select("select DATE_FORMAT(curtime,'%Y-%m-%d') as name,count(*) as num from ip where curtime>#{time} group by name order by name desc limit 5")
	List<Chart> listDay5(@Param("time") Timestamp time);
}
