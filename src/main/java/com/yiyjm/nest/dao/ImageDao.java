package com.yiyjm.nest.dao;

import com.yiyjm.nest.entity.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图像 Dao
 *
 * @author jonny
 * @date 2020/04/30
 */
@Mapper
@Repository
@Service
public interface ImageDao {
	/**
	 * 插入图片
	 *
	 * @param image 图像
	 * @return int
	 */
	@Insert("insert into image(bid,name,pubtime) values(#{bid},#{name},now())")
	int insertImage(Image image);

	/**
	 * 上传图片列表
	 *
	 * @param iid    iid
	 * @param number 数量
	 * @return {@link List<Image>}
	 */
	@Select("select * from image where bid=-1 and iid<#{iid} order by iid desc limit #{number}")
	List<Image> listUploadImage(@Param("iid") long iid, @Param("number") int number);

	/**
	 * 图像列表页面
	 *
	 * @param cur    坏蛋
	 * @param number 数量
	 * @return {@link List<Image>}
	 */
	@Select("select * from image order by iid desc limit #{cur},#{number}")
	List<Image> listImageByPage(@Param("cur") int cur, @Param("number") int number);

	/**
	 * 删除图片
	 *
	 * @param iid iid
	 * @return int
	 */
	@Delete("delete from image where iid = #{iid}")
	int deleteImage(@Param("iid") int iid);

	/**
	 * 查询图像
	 *
	 * @param iid iid
	 * @return {@link Image}
	 */
	@Select("select * from image where iid = #{iid}")
	Image queryImage(@Param("iid") int iid);

	/**
	 * get 总
	 * 得到总
	 *
	 * @return int
	 */
	@Select("select count(*) from image")
	int getTotal();
}
