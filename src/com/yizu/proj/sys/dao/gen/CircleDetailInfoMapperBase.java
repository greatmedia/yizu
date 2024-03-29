package com.yizu.proj.sys.dao.gen;

import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table circledetailinfo
*/
public interface CircleDetailInfoMapperBase {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param example CircleDetailInfoExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int countByExample(CircleDetailInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param example CircleDetailInfoExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int deleteByExample(CircleDetailInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param circledetailid String
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String circledetailid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param record CircleDetailInfo
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int insert(CircleDetailInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param record CircleDetailInfo
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int insertSelective(CircleDetailInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param example CircleDetailInfoExample
     *
     * @return the value of java.util.List<com.yizu.proj.sys.beans.CircleDetailInfo>
     *
     * @mbggenerated
     */
    List<CircleDetailInfo> selectByExample(CircleDetailInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param circledetailid String
     *
     * @return the value of com.yizu.proj.sys.beans.CircleDetailInfo
     *
     * @mbggenerated
     */
    CircleDetailInfo selectByPrimaryKey(String circledetailid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param record CircleDetailInfo
     * @param example CircleDetailInfoExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CircleDetailInfo record, @Param("example") CircleDetailInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param record CircleDetailInfo
     * @param example CircleDetailInfoExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CircleDetailInfo record, @Param("example") CircleDetailInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param record CircleDetailInfo
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CircleDetailInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circledetailinfo
     *
     * @param record CircleDetailInfo
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CircleDetailInfo record);
}