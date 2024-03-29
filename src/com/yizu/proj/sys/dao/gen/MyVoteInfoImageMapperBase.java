package com.yizu.proj.sys.dao.gen;

import com.yizu.proj.sys.beans.MyVoteInfoImage;
import com.yizu.proj.sys.beans.MyVoteInfoImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table myvoteinfo_image
*/
public interface MyVoteInfoImageMapperBase {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param example MyVoteInfoImageExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int countByExample(MyVoteInfoImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param example MyVoteInfoImageExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int deleteByExample(MyVoteInfoImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param imageId String
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String imageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param record MyVoteInfoImage
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int insert(MyVoteInfoImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param record MyVoteInfoImage
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int insertSelective(MyVoteInfoImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param example MyVoteInfoImageExample
     *
     * @return the value of java.util.List<com.yizu.proj.sys.beans.MyVoteInfoImage>
     *
     * @mbggenerated
     */
    List<MyVoteInfoImage> selectByExample(MyVoteInfoImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param imageId String
     *
     * @return the value of com.yizu.proj.sys.beans.MyVoteInfoImage
     *
     * @mbggenerated
     */
    MyVoteInfoImage selectByPrimaryKey(String imageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param record MyVoteInfoImage
     * @param example MyVoteInfoImageExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MyVoteInfoImage record, @Param("example") MyVoteInfoImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param record MyVoteInfoImage
     * @param example MyVoteInfoImageExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MyVoteInfoImage record, @Param("example") MyVoteInfoImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param record MyVoteInfoImage
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MyVoteInfoImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table myvoteinfo_image
     *
     * @param record MyVoteInfoImage
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MyVoteInfoImage record);
}