package com.yizu.proj.sys.beans.gen;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table collect
*/
public class CollectBase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collect.cid
     *
     * @mbggenerated
     */
    protected String cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collect.circleId
     *
     * @mbggenerated
     */
    protected String circleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collect.userId
     *
     * @mbggenerated
     */
    protected Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collect.collectDateTime
     *
     * @mbggenerated
     */
    protected Date collectdatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collect.ctype
     *
     * @mbggenerated
     */
    protected String ctype;

    /**
     .
     * This method returns the value of the database columncollect.cid
     *
     * @return the value of collect.cid
     *
     * @mbggenerated
     */
    public String getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collect.cid
     *
     * @param cid the value for collect.cid
     *
     * @mbggenerated
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    /**
     .
     * This method returns the value of the database columncollect.circleId
     *
     * @return the value of collect.circleId
     *
     * @mbggenerated
     */
    public String getCircleid() {
        return circleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collect.circleId
     *
     * @param circleid the value for collect.circleId
     *
     * @mbggenerated
     */
    public void setCircleid(String circleid) {
        this.circleid = circleid == null ? null : circleid.trim();
    }

    /**
     .
     * This method returns the value of the database columncollect.userId
     *
     * @return the value of collect.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collect.userId
     *
     * @param userid the value for collect.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     .
     * This method returns the value of the database columncollect.collectDateTime
     *
     * @return the value of collect.collectDateTime
     *
     * @mbggenerated
     */
    public Date getCollectdatetime() {
        return collectdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collect.collectDateTime
     *
     * @param collectdatetime the value for collect.collectDateTime
     *
     * @mbggenerated
     */
    public void setCollectdatetime(Date collectdatetime) {
        this.collectdatetime = collectdatetime;
    }

    /**
     .
     * This method returns the value of the database columncollect.ctype
     *
     * @return the value of collect.ctype
     *
     * @mbggenerated
     */
    public String getCtype() {
        return ctype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collect.ctype
     *
     * @param ctype the value for collect.ctype
     *
     * @mbggenerated
     */
    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }
}