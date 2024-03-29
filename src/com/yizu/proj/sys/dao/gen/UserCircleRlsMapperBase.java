package com.yizu.proj.sys.dao.gen;

import com.yizu.proj.sys.beans.UserCircleRls;
import com.yizu.proj.sys.beans.UserCircleRlsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_circle_rls
*/
public interface UserCircleRlsMapperBase {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param example UserCircleRlsExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int countByExample(UserCircleRlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param example UserCircleRlsExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int deleteByExample(UserCircleRlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param ucid String
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String ucid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param record UserCircleRls
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int insert(UserCircleRls record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param record UserCircleRls
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int insertSelective(UserCircleRls record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param example UserCircleRlsExample
     *
     * @return the value of java.util.List<com.yizu.proj.sys.beans.UserCircleRls>
     *
     * @mbggenerated
     */
    List<UserCircleRls> selectByExample(UserCircleRlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param ucid String
     *
     * @return the value of com.yizu.proj.sys.beans.UserCircleRls
     *
     * @mbggenerated
     */
    UserCircleRls selectByPrimaryKey(String ucid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param record UserCircleRls
     * @param example UserCircleRlsExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UserCircleRls record, @Param("example") UserCircleRlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param record UserCircleRls
     * @param example UserCircleRlsExample
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UserCircleRls record, @Param("example") UserCircleRlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param record UserCircleRls
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserCircleRls record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_circle_rls
     *
     * @param record UserCircleRls
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserCircleRls record);
}