package com.yizu.proj.sys.beans.gen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table************** tagtopic
 */
/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table************** tagtopic
 */
/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table************** tagtopic
 */
public class TagTopicExampleBase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tagtopic
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tagtopic
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tagtopic
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tagtopic
     *
     * @mbggenerated
     */
    protected int limitStart = -1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tagtopic
     *
     * @mbggenerated
     */
    protected int rowsPerPage = -1;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tagtopic
     *
     *
     *
     * @mbggenerated
     */
    public TagTopicExampleBase() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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
     * This method corresponds to the database table tagtopic
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

        /**
         *addCriterion("ttid is null");
         * @return Criteria this
         */
        public Criteria andTtidIsNull() {
            addCriterion("ttid is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("ttid is not null");
         * @return Criteria this
         */
        public Criteria andTtidIsNotNull() {
            addCriterion("ttid is not null");
            return (Criteria) this;
        }

        /**
         *andTtidEqualTo
         * @param value ttid
         * @return Criteria this
         */
        public Criteria andTtidEqualTo(String value) {
            addCriterion("ttid =", value, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidNotEqualTo
         * @param value ttid
         * @return Criteria this
         */
        public Criteria andTtidNotEqualTo(String value) {
            addCriterion("ttid <>", value, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidGreaterThan
         * @param value ttid
         * @return Criteria this
         */
        public Criteria andTtidGreaterThan(String value) {
            addCriterion("ttid >", value, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidGreaterThanOrEqualTo
         * @param value ttid
         * @return Criteria this
         */
        public Criteria andTtidGreaterThanOrEqualTo(String value) {
            addCriterion("ttid >=", value, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidLessThan
         * @param value ttid
         * @return Criteria this
         */
        public Criteria andTtidLessThan(String value) {
            addCriterion("ttid <", value, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidLessThanOrEqualTo
         * @param value ttid
         * @return Criteria this
         */
        public Criteria andTtidLessThanOrEqualTo(String value) {
            addCriterion("ttid <=", value, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidLike
         * @param value ttid
         * @return Criteria this
         */
        public Criteria andTtidLike(String value) {
            addCriterion("ttid like", value, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidNotLike
         * @param value ttid
         * @return Criteria this
         */
        public Criteria andTtidNotLike(String value) {
            addCriterion("ttid not like", value, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTtidIn(List<String> values) {
            addCriterion("ttid in", values, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidNotIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTtidNotIn(List<String> values) {
            addCriterion("ttid not in", values, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTtidBetween(String value1, String value2) {
            addCriterion("ttid between", value1, value2, "ttid");
            return (Criteria) this;
        }

        /**
         *andTtidNotBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTtidNotBetween(String value1, String value2) {
            addCriterion("ttid not between", value1, value2, "ttid");
            return (Criteria) this;
        }

        /**
         *addCriterion("tagName is null");
         * @return Criteria this
         */
        public Criteria andTagnameIsNull() {
            addCriterion("tagName is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("tagName is not null");
         * @return Criteria this
         */
        public Criteria andTagnameIsNotNull() {
            addCriterion("tagName is not null");
            return (Criteria) this;
        }

        /**
         *andTagnameEqualTo
         * @param value tagname
         * @return Criteria this
         */
        public Criteria andTagnameEqualTo(String value) {
            addCriterion("tagName =", value, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameNotEqualTo
         * @param value tagname
         * @return Criteria this
         */
        public Criteria andTagnameNotEqualTo(String value) {
            addCriterion("tagName <>", value, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameGreaterThan
         * @param value tagname
         * @return Criteria this
         */
        public Criteria andTagnameGreaterThan(String value) {
            addCriterion("tagName >", value, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameGreaterThanOrEqualTo
         * @param value tagname
         * @return Criteria this
         */
        public Criteria andTagnameGreaterThanOrEqualTo(String value) {
            addCriterion("tagName >=", value, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameLessThan
         * @param value tagname
         * @return Criteria this
         */
        public Criteria andTagnameLessThan(String value) {
            addCriterion("tagName <", value, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameLessThanOrEqualTo
         * @param value tagname
         * @return Criteria this
         */
        public Criteria andTagnameLessThanOrEqualTo(String value) {
            addCriterion("tagName <=", value, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameLike
         * @param value tagname
         * @return Criteria this
         */
        public Criteria andTagnameLike(String value) {
            addCriterion("tagName like", value, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameNotLike
         * @param value tagname
         * @return Criteria this
         */
        public Criteria andTagnameNotLike(String value) {
            addCriterion("tagName not like", value, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTagnameIn(List<String> values) {
            addCriterion("tagName in", values, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameNotIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTagnameNotIn(List<String> values) {
            addCriterion("tagName not in", values, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTagnameBetween(String value1, String value2) {
            addCriterion("tagName between", value1, value2, "tagname");
            return (Criteria) this;
        }

        /**
         *andTagnameNotBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTagnameNotBetween(String value1, String value2) {
            addCriterion("tagName not between", value1, value2, "tagname");
            return (Criteria) this;
        }

        /**
         *addCriterion("tDateTime is null");
         * @return Criteria this
         */
        public Criteria andTdatetimeIsNull() {
            addCriterion("tDateTime is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("tDateTime is not null");
         * @return Criteria this
         */
        public Criteria andTdatetimeIsNotNull() {
            addCriterion("tDateTime is not null");
            return (Criteria) this;
        }

        /**
         *andTdatetimeEqualTo
         * @param value tdatetime
         * @return Criteria this
         */
        public Criteria andTdatetimeEqualTo(Date value) {
            addCriterion("tDateTime =", value, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeNotEqualTo
         * @param value tdatetime
         * @return Criteria this
         */
        public Criteria andTdatetimeNotEqualTo(Date value) {
            addCriterion("tDateTime <>", value, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeGreaterThan
         * @param value tdatetime
         * @return Criteria this
         */
        public Criteria andTdatetimeGreaterThan(Date value) {
            addCriterion("tDateTime >", value, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeGreaterThanOrEqualTo
         * @param value tdatetime
         * @return Criteria this
         */
        public Criteria andTdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tDateTime >=", value, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeLessThan
         * @param value tdatetime
         * @return Criteria this
         */
        public Criteria andTdatetimeLessThan(Date value) {
            addCriterion("tDateTime <", value, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeLessThanOrEqualTo
         * @param value tdatetime
         * @return Criteria this
         */
        public Criteria andTdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("tDateTime <=", value, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTdatetimeIn(List<Date> values) {
            addCriterion("tDateTime in", values, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeNotIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTdatetimeNotIn(List<Date> values) {
            addCriterion("tDateTime not in", values, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTdatetimeBetween(Date value1, Date value2) {
            addCriterion("tDateTime between", value1, value2, "tdatetime");
            return (Criteria) this;
        }

        /**
         *andTdatetimeNotBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("tDateTime not between", value1, value2, "tdatetime");
            return (Criteria) this;
        }

        /**
         *addCriterion("tagtype is null");
         * @return Criteria this
         */
        public Criteria andTagtypeIsNull() {
            addCriterion("tagtype is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("tagtype is not null");
         * @return Criteria this
         */
        public Criteria andTagtypeIsNotNull() {
            addCriterion("tagtype is not null");
            return (Criteria) this;
        }

        /**
         *andTagtypeEqualTo
         * @param value tagtype
         * @return Criteria this
         */
        public Criteria andTagtypeEqualTo(Integer value) {
            addCriterion("tagtype =", value, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeNotEqualTo
         * @param value tagtype
         * @return Criteria this
         */
        public Criteria andTagtypeNotEqualTo(Integer value) {
            addCriterion("tagtype <>", value, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeGreaterThan
         * @param value tagtype
         * @return Criteria this
         */
        public Criteria andTagtypeGreaterThan(Integer value) {
            addCriterion("tagtype >", value, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeGreaterThanOrEqualTo
         * @param value tagtype
         * @return Criteria this
         */
        public Criteria andTagtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tagtype >=", value, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeLessThan
         * @param value tagtype
         * @return Criteria this
         */
        public Criteria andTagtypeLessThan(Integer value) {
            addCriterion("tagtype <", value, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeLessThanOrEqualTo
         * @param value tagtype
         * @return Criteria this
         */
        public Criteria andTagtypeLessThanOrEqualTo(Integer value) {
            addCriterion("tagtype <=", value, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTagtypeIn(List<Integer> values) {
            addCriterion("tagtype in", values, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeNotIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTagtypeNotIn(List<Integer> values) {
            addCriterion("tagtype not in", values, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTagtypeBetween(Integer value1, Integer value2) {
            addCriterion("tagtype between", value1, value2, "tagtype");
            return (Criteria) this;
        }

        /**
         *andTagtypeNotBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTagtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tagtype not between", value1, value2, "tagtype");
            return (Criteria) this;
        }

        /**
         *addCriterion("tsort is null");
         * @return Criteria this
         */
        public Criteria andTsortIsNull() {
            addCriterion("tsort is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("tsort is not null");
         * @return Criteria this
         */
        public Criteria andTsortIsNotNull() {
            addCriterion("tsort is not null");
            return (Criteria) this;
        }

        /**
         *andTsortEqualTo
         * @param value tsort
         * @return Criteria this
         */
        public Criteria andTsortEqualTo(Integer value) {
            addCriterion("tsort =", value, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortNotEqualTo
         * @param value tsort
         * @return Criteria this
         */
        public Criteria andTsortNotEqualTo(Integer value) {
            addCriterion("tsort <>", value, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortGreaterThan
         * @param value tsort
         * @return Criteria this
         */
        public Criteria andTsortGreaterThan(Integer value) {
            addCriterion("tsort >", value, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortGreaterThanOrEqualTo
         * @param value tsort
         * @return Criteria this
         */
        public Criteria andTsortGreaterThanOrEqualTo(Integer value) {
            addCriterion("tsort >=", value, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortLessThan
         * @param value tsort
         * @return Criteria this
         */
        public Criteria andTsortLessThan(Integer value) {
            addCriterion("tsort <", value, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortLessThanOrEqualTo
         * @param value tsort
         * @return Criteria this
         */
        public Criteria andTsortLessThanOrEqualTo(Integer value) {
            addCriterion("tsort <=", value, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTsortIn(List<Integer> values) {
            addCriterion("tsort in", values, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortNotIn
         * @param values
         * @return Criteria this
         */
        public Criteria andTsortNotIn(List<Integer> values) {
            addCriterion("tsort not in", values, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTsortBetween(Integer value1, Integer value2) {
            addCriterion("tsort between", value1, value2, "tsort");
            return (Criteria) this;
        }

        /**
         *andTsortNotBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andTsortNotBetween(Integer value1, Integer value2) {
            addCriterion("tsort not between", value1, value2, "tsort");
            return (Criteria) this;
        }

        /**
         *addCriterion("display is null");
         * @return Criteria this
         */
        public Criteria andDisplayIsNull() {
            addCriterion("display is null");
            return (Criteria) this;
        }

        /**
         *addCriterion("display is not null");
         * @return Criteria this
         */
        public Criteria andDisplayIsNotNull() {
            addCriterion("display is not null");
            return (Criteria) this;
        }

        /**
         *andDisplayEqualTo
         * @param value display
         * @return Criteria this
         */
        public Criteria andDisplayEqualTo(Integer value) {
            addCriterion("display =", value, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayNotEqualTo
         * @param value display
         * @return Criteria this
         */
        public Criteria andDisplayNotEqualTo(Integer value) {
            addCriterion("display <>", value, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayGreaterThan
         * @param value display
         * @return Criteria this
         */
        public Criteria andDisplayGreaterThan(Integer value) {
            addCriterion("display >", value, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayGreaterThanOrEqualTo
         * @param value display
         * @return Criteria this
         */
        public Criteria andDisplayGreaterThanOrEqualTo(Integer value) {
            addCriterion("display >=", value, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayLessThan
         * @param value display
         * @return Criteria this
         */
        public Criteria andDisplayLessThan(Integer value) {
            addCriterion("display <", value, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayLessThanOrEqualTo
         * @param value display
         * @return Criteria this
         */
        public Criteria andDisplayLessThanOrEqualTo(Integer value) {
            addCriterion("display <=", value, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayIn
         * @param values
         * @return Criteria this
         */
        public Criteria andDisplayIn(List<Integer> values) {
            addCriterion("display in", values, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayNotIn
         * @param values
         * @return Criteria this
         */
        public Criteria andDisplayNotIn(List<Integer> values) {
            addCriterion("display not in", values, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andDisplayBetween(Integer value1, Integer value2) {
            addCriterion("display between", value1, value2, "display");
            return (Criteria) this;
        }

        /**
         *andDisplayNotBetween
         * @param value1
         * @param value2
         * @return Criteria this
         */
        public Criteria andDisplayNotBetween(Integer value1, Integer value2) {
            addCriterion("display not between", value1, value2, "display");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tagtopic
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