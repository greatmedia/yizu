package com.yizu.proj.sys.dao.gen;

import com.yizu.proj.sys.beans.City;
import com.yizu.proj.sys.beans.CityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CityMapperBase {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int countByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int deleteByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int deleteByPrimaryKey(Integer cityid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int insert(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int insertSelective(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    List<City> selectByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    City selectByPrimaryKey(Integer cityid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int updateByExampleSelective(@Param("record") City record, @Param("example") CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int updateByExample(@Param("record") City record, @Param("example") CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int updateByPrimaryKeySelective(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    int updateByPrimaryKey(City record);
}