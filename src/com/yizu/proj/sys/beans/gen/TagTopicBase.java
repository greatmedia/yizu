package com.yizu.proj.sys.beans.gen;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tagtopic
*/
public class TagTopicBase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tagtopic.ttid
     *
     * @mbggenerated
     */
    protected String ttid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tagtopic.tagName
     *
     * @mbggenerated
     */
    protected String tagname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tagtopic.tDateTime
     *
     * @mbggenerated
     */
    protected Date tdatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tagtopic.tagtype
     *
     * @mbggenerated
     */
    protected Integer tagtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tagtopic.tsort
     *
     * @mbggenerated
     */
    protected Integer tsort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tagtopic.display
     *
     * @mbggenerated
     */
    protected Integer display;

    /**
     .
     * This method returns the value of the database columntagtopic.ttid
     *
     * @return the value of tagtopic.ttid
     *
     * @mbggenerated
     */
    public String getTtid() {
        return ttid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tagtopic.ttid
     *
     * @param ttid the value for tagtopic.ttid
     *
     * @mbggenerated
     */
    public void setTtid(String ttid) {
        this.ttid = ttid == null ? null : ttid.trim();
    }

    /**
     .
     * This method returns the value of the database columntagtopic.tagName
     *
     * @return the value of tagtopic.tagName
     *
     * @mbggenerated
     */
    public String getTagname() {
        return tagname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tagtopic.tagName
     *
     * @param tagname the value for tagtopic.tagName
     *
     * @mbggenerated
     */
    public void setTagname(String tagname) {
        this.tagname = tagname == null ? null : tagname.trim();
    }

    /**
     .
     * This method returns the value of the database columntagtopic.tDateTime
     *
     * @return the value of tagtopic.tDateTime
     *
     * @mbggenerated
     */
    public Date getTdatetime() {
        return tdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tagtopic.tDateTime
     *
     * @param tdatetime the value for tagtopic.tDateTime
     *
     * @mbggenerated
     */
    public void setTdatetime(Date tdatetime) {
        this.tdatetime = tdatetime;
    }

    /**
     .
     * This method returns the value of the database columntagtopic.tagtype
     *
     * @return the value of tagtopic.tagtype
     *
     * @mbggenerated
     */
    public Integer getTagtype() {
        return tagtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tagtopic.tagtype
     *
     * @param tagtype the value for tagtopic.tagtype
     *
     * @mbggenerated
     */
    public void setTagtype(Integer tagtype) {
        this.tagtype = tagtype;
    }

    /**
     .
     * This method returns the value of the database columntagtopic.tsort
     *
     * @return the value of tagtopic.tsort
     *
     * @mbggenerated
     */
    public Integer getTsort() {
        return tsort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tagtopic.tsort
     *
     * @param tsort the value for tagtopic.tsort
     *
     * @mbggenerated
     */
    public void setTsort(Integer tsort) {
        this.tsort = tsort;
    }

    /**
     .
     * This method returns the value of the database columntagtopic.display
     *
     * @return the value of tagtopic.display
     *
     * @mbggenerated
     */
    public Integer getDisplay() {
        return display;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tagtopic.display
     *
     * @param display the value for tagtopic.display
     *
     * @mbggenerated
     */
    public void setDisplay(Integer display) {
        this.display = display;
    }
}