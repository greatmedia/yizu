package com.yizu.proj.sys.beans.gen;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table************** tfind_pwd_log
*/
/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table************** tfind_pwd_log
*/
/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table************** tfind_pwd_log
*/
public class TfindPwdLogExampleBase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tfind_pwd_log
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tfind_pwd_log
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tfind_pwd_log
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tfind_pwd_log
     *
     * @mbggenerated
     */
    protected int limitStart = -1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tfind_pwd_log
     *
     * @mbggenerated
     */
    protected int rowsPerPage = -1;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     *
     * @mbggenerated
     */
    public TfindPwdLogExampleBase() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     * @param orderByClause String
     *
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     * @return the value of java.lang.String
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     * @param distinct boolean
     *
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     * @return the value of boolean
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     * @return the value of java.util.List<Criteria>
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     * @param criteria Criteria
     *
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     * @return the value of Criteria
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     * @return the value of Criteria
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     * @return the value of Criteria
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     * @param limitStart int
     *
     *
     * @mbggenerated
     */
    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    public int getLimitStart() {
        return limitStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     * @param rowsPerPage int
     *
     *
     * @mbggenerated
     */
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage=rowsPerPage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tfind_pwd_log
     *
     *
     * @return the value of int
     *
     * @mbggenerated
     */
    public int getRowsPerPage() {
        return rowsPerPage;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table 
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        /**
         * criteria
         */
        protected List<Criterion> criteria;

        /**
         * 构造方法
        */
        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        /**
         * isValid 方法
         * 
         * @return boolean 
         */
        public boolean isValid() {
            return criteria.size() > 0;
        }

        /**
         * getAllCriteria方法
         * @return criteria
         */
        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        /**
         * getCriteria方法
         * @return criteria
         */
        public List<Criterion> getCriteria() {
            return criteria;
        }

        /**
         * addCriterion方法
         * @param condition 字符串
         */
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        /**
         * addCriterion方法
         * @param condition 字符串
         * @param value Object对象
         * @param property 字符串
         */
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /**
         * addCriterion方法
         * @param condition 字符串
         * @param value1 Object对象
         * @param value2 Object对象
         * @param property 字符串
         */
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        /**
         *addCriterion("ID is null");
         * @return Criteria this
        */
        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("ID is not null");
         * @return Criteria this
        */
        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        /**
         *andIdEqualTo
         * @param value id
         * @return Criteria this
        */
        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        /**
         *andIdNotEqualTo
         * @param value id
         * @return Criteria this
        */
        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        /**
         *andIdGreaterThan
         * @param value id
         * @return Criteria this
        */
        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        /**
         *andIdGreaterThanOrEqualTo
         * @param value id
         * @return Criteria this
        */
        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        /**
         *andIdLessThan
         * @param value id
         * @return Criteria this
        */
        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        /**
         *andIdLessThanOrEqualTo
         * @param value id
         * @return Criteria this
        */
        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        /**
         *andIdLike
         * @param value id
         * @return Criteria this
        */
        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        /**
         *andIdNotLike
         * @param value id
         * @return Criteria this
        */
        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        /**
         *andIdIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        /**
         *andIdNotIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        /**
         *andIdBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        /**
         *andIdNotBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        /**
         *addCriterion("USER_ID is null");
         * @return Criteria this
        */
        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("USER_ID is not null");
         * @return Criteria this
        */
        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        /**
         *andUserIdEqualTo
         * @param value userId
         * @return Criteria this
        */
        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdNotEqualTo
         * @param value userId
         * @return Criteria this
        */
        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdGreaterThan
         * @param value userId
         * @return Criteria this
        */
        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdGreaterThanOrEqualTo
         * @param value userId
         * @return Criteria this
        */
        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdLessThan
         * @param value userId
         * @return Criteria this
        */
        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdLessThanOrEqualTo
         * @param value userId
         * @return Criteria this
        */
        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdLike
         * @param value userId
         * @return Criteria this
        */
        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdNotLike
         * @param value userId
         * @return Criteria this
        */
        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdNotIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        /**
         *andUserIdNotBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        /**
         *addCriterion("CREATE_TIME is null");
         * @return Criteria this
        */
        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("CREATE_TIME is not null");
         * @return Criteria this
        */
        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        /**
         *andCreateTimeEqualTo
         * @param value createTime
         * @return Criteria this
        */
        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeNotEqualTo
         * @param value createTime
         * @return Criteria this
        */
        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeGreaterThan
         * @param value createTime
         * @return Criteria this
        */
        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeGreaterThanOrEqualTo
         * @param value createTime
         * @return Criteria this
        */
        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeLessThan
         * @param value createTime
         * @return Criteria this
        */
        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeLessThanOrEqualTo
         * @param value createTime
         * @return Criteria this
        */
        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeNotIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        /**
         *andCreateTimeNotBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        /**
         *addCriterion("CODE is null");
         * @return Criteria this
        */
        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("CODE is not null");
         * @return Criteria this
        */
        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        /**
         *andCodeEqualTo
         * @param value code
         * @return Criteria this
        */
        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        /**
         *andCodeNotEqualTo
         * @param value code
         * @return Criteria this
        */
        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        /**
         *andCodeGreaterThan
         * @param value code
         * @return Criteria this
        */
        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        /**
         *andCodeGreaterThanOrEqualTo
         * @param value code
         * @return Criteria this
        */
        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        /**
         *andCodeLessThan
         * @param value code
         * @return Criteria this
        */
        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        /**
         *andCodeLessThanOrEqualTo
         * @param value code
         * @return Criteria this
        */
        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        /**
         *andCodeLike
         * @param value code
         * @return Criteria this
        */
        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        /**
         *andCodeNotLike
         * @param value code
         * @return Criteria this
        */
        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        /**
         *andCodeIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        /**
         *andCodeNotIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        /**
         *andCodeBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        /**
         *andCodeNotBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        /**
         *addCriterion("IP is null");
         * @return Criteria this
        */
        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("IP is not null");
         * @return Criteria this
        */
        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        /**
         *andIpEqualTo
         * @param value ip
         * @return Criteria this
        */
        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "ip");
            return (Criteria) this;
        }

        /**
         *andIpNotEqualTo
         * @param value ip
         * @return Criteria this
        */
        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "ip");
            return (Criteria) this;
        }

        /**
         *andIpGreaterThan
         * @param value ip
         * @return Criteria this
        */
        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "ip");
            return (Criteria) this;
        }

        /**
         *andIpGreaterThanOrEqualTo
         * @param value ip
         * @return Criteria this
        */
        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "ip");
            return (Criteria) this;
        }

        /**
         *andIpLessThan
         * @param value ip
         * @return Criteria this
        */
        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "ip");
            return (Criteria) this;
        }

        /**
         *andIpLessThanOrEqualTo
         * @param value ip
         * @return Criteria this
        */
        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "ip");
            return (Criteria) this;
        }

        /**
         *andIpLike
         * @param value ip
         * @return Criteria this
        */
        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "ip");
            return (Criteria) this;
        }

        /**
         *andIpNotLike
         * @param value ip
         * @return Criteria this
        */
        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "ip");
            return (Criteria) this;
        }

        /**
         *andIpIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "ip");
            return (Criteria) this;
        }

        /**
         *andIpNotIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "ip");
            return (Criteria) this;
        }

        /**
         *andIpBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "ip");
            return (Criteria) this;
        }

        /**
         *andIpNotBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "ip");
            return (Criteria) this;
        }

        /**
         *addCriterion("USED_TIME is null");
         * @return Criteria this
        */
        public Criteria andUsedTimeIsNull() {
            addCriterion("USED_TIME is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("USED_TIME is not null");
         * @return Criteria this
        */
        public Criteria andUsedTimeIsNotNull() {
            addCriterion("USED_TIME is not null");
            return (Criteria) this;
        }

        /**
         *andUsedTimeEqualTo
         * @param value usedTime
         * @return Criteria this
        */
        public Criteria andUsedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("USED_TIME =", value, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeNotEqualTo
         * @param value usedTime
         * @return Criteria this
        */
        public Criteria andUsedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("USED_TIME <>", value, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeGreaterThan
         * @param value usedTime
         * @return Criteria this
        */
        public Criteria andUsedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("USED_TIME >", value, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeGreaterThanOrEqualTo
         * @param value usedTime
         * @return Criteria this
        */
        public Criteria andUsedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("USED_TIME >=", value, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeLessThan
         * @param value usedTime
         * @return Criteria this
        */
        public Criteria andUsedTimeLessThan(Date value) {
            addCriterionForJDBCDate("USED_TIME <", value, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeLessThanOrEqualTo
         * @param value usedTime
         * @return Criteria this
        */
        public Criteria andUsedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("USED_TIME <=", value, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andUsedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("USED_TIME in", values, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeNotIn
         * @param values 
         * @return Criteria this
        */
        public Criteria andUsedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("USED_TIME not in", values, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andUsedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("USED_TIME between", value1, value2, "usedTime");
            return (Criteria) this;
        }

        /**
         *andUsedTimeNotBetween
         * @param value1 
         * @param value2 
         * @return Criteria this
        */
        public Criteria andUsedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("USED_TIME not between", value1, value2, "usedTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tfind_pwd_log
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        /**
         * 构造方法
         * 继承父类
        */
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table 
     *
     * @mbggenerated
     */
    public static class Criterion {
        /**
         * condition
         */
        private String condition;

        /**
         * value
         */
        private Object value;

        /**
         * secondValue
         */
        private Object secondValue;

        /**
         * noValue
         */
        private boolean noValue;

        /**
         * singleValue
         */
        private boolean singleValue;

        /**
         * betweenValue
         */
        private boolean betweenValue;

        /**
         * listValue
         */
        private boolean listValue;

        /**
         * typeHandler
         */
        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        /**
         * Criterion构造方法
         * 继承父类
         * @param condition 字符串
         */
        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        /**
         * Criterion构造方法
         * 继承父类
         * @param condition 字符串
         * @param value object对象
         * @param typeHandler 字符串
         */
        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        /**
         * Criterion构造方法
         * @param condition 字符串
         * @param value object对象
         */
        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        /**
         * Criterion构造方法
         * 继承父类
         * @param condition 字符串
         * @param value object对象
         * @param secondValue object对象
         * @param typeHandler 字符串
         */
        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        /**
         * Criterion构造方法
         * 继承父类
         * @param condition 字符串
         * @param value object对象
         * @param secondValue object对象
         */
        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}