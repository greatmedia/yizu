package com.yizu.proj.sys.beans.gen;

public class ProvinceBase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.provinceID
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    private Integer provinceid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.name
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.validFlag
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    private String validflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.deliveryFee
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    private Float deliveryfee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.freeMinTotalPrice
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    private Float freemintotalprice;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.provinceID
     *
     * @return the value of province.provinceID
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public Integer getProvinceid() {
        return provinceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.provinceID
     *
     * @param provinceid the value for province.provinceID
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.name
     *
     * @return the value of province.name
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.name
     *
     * @param name the value for province.name
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.validFlag
     *
     * @return the value of province.validFlag
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public String getValidflag() {
        return validflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.validFlag
     *
     * @param validflag the value for province.validFlag
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void setValidflag(String validflag) {
        this.validflag = validflag == null ? null : validflag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.deliveryFee
     *
     * @return the value of province.deliveryFee
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public Float getDeliveryfee() {
        return deliveryfee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.deliveryFee
     *
     * @param deliveryfee the value for province.deliveryFee
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void setDeliveryfee(Float deliveryfee) {
        this.deliveryfee = deliveryfee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.freeMinTotalPrice
     *
     * @return the value of province.freeMinTotalPrice
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public Float getFreemintotalprice() {
        return freemintotalprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.freeMinTotalPrice
     *
     * @param freemintotalprice the value for province.freeMinTotalPrice
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void setFreemintotalprice(Float freemintotalprice) {
        this.freemintotalprice = freemintotalprice;
    }
}