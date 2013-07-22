package com.yizu.proj.sys.beans.gen;

import java.util.ArrayList;
import java.util.List;

public class TownExampleBase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public TownExampleBase() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
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
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTownidIsNull() {
            addCriterion("townID is null");
            return (Criteria) this;
        }

        public Criteria andTownidIsNotNull() {
            addCriterion("townID is not null");
            return (Criteria) this;
        }

        public Criteria andTownidEqualTo(Integer value) {
            addCriterion("townID =", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidNotEqualTo(Integer value) {
            addCriterion("townID <>", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidGreaterThan(Integer value) {
            addCriterion("townID >", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidGreaterThanOrEqualTo(Integer value) {
            addCriterion("townID >=", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidLessThan(Integer value) {
            addCriterion("townID <", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidLessThanOrEqualTo(Integer value) {
            addCriterion("townID <=", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidIn(List<Integer> values) {
            addCriterion("townID in", values, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidNotIn(List<Integer> values) {
            addCriterion("townID not in", values, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidBetween(Integer value1, Integer value2) {
            addCriterion("townID between", value1, value2, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidNotBetween(Integer value1, Integer value2) {
            addCriterion("townID not between", value1, value2, "townid");
            return (Criteria) this;
        }

        public Criteria andCityidIsNull() {
            addCriterion("cityID is null");
            return (Criteria) this;
        }

        public Criteria andCityidIsNotNull() {
            addCriterion("cityID is not null");
            return (Criteria) this;
        }

        public Criteria andCityidEqualTo(Integer value) {
            addCriterion("cityID =", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotEqualTo(Integer value) {
            addCriterion("cityID <>", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThan(Integer value) {
            addCriterion("cityID >", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cityID >=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThan(Integer value) {
            addCriterion("cityID <", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThanOrEqualTo(Integer value) {
            addCriterion("cityID <=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidIn(List<Integer> values) {
            addCriterion("cityID in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotIn(List<Integer> values) {
            addCriterion("cityID not in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidBetween(Integer value1, Integer value2) {
            addCriterion("cityID between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotBetween(Integer value1, Integer value2) {
            addCriterion("cityID not between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andZipIsNull() {
            addCriterion("zip is null");
            return (Criteria) this;
        }

        public Criteria andZipIsNotNull() {
            addCriterion("zip is not null");
            return (Criteria) this;
        }

        public Criteria andZipEqualTo(String value) {
            addCriterion("zip =", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipNotEqualTo(String value) {
            addCriterion("zip <>", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipGreaterThan(String value) {
            addCriterion("zip >", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipGreaterThanOrEqualTo(String value) {
            addCriterion("zip >=", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipLessThan(String value) {
            addCriterion("zip <", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipLessThanOrEqualTo(String value) {
            addCriterion("zip <=", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipLike(String value) {
            addCriterion("zip like", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipNotLike(String value) {
            addCriterion("zip not like", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipIn(List<String> values) {
            addCriterion("zip in", values, "zip");
            return (Criteria) this;
        }

        public Criteria andZipNotIn(List<String> values) {
            addCriterion("zip not in", values, "zip");
            return (Criteria) this;
        }

        public Criteria andZipBetween(String value1, String value2) {
            addCriterion("zip between", value1, value2, "zip");
            return (Criteria) this;
        }

        public Criteria andZipNotBetween(String value1, String value2) {
            addCriterion("zip not between", value1, value2, "zip");
            return (Criteria) this;
        }

        public Criteria andTelzipIsNull() {
            addCriterion("telZip is null");
            return (Criteria) this;
        }

        public Criteria andTelzipIsNotNull() {
            addCriterion("telZip is not null");
            return (Criteria) this;
        }

        public Criteria andTelzipEqualTo(String value) {
            addCriterion("telZip =", value, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipNotEqualTo(String value) {
            addCriterion("telZip <>", value, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipGreaterThan(String value) {
            addCriterion("telZip >", value, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipGreaterThanOrEqualTo(String value) {
            addCriterion("telZip >=", value, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipLessThan(String value) {
            addCriterion("telZip <", value, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipLessThanOrEqualTo(String value) {
            addCriterion("telZip <=", value, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipLike(String value) {
            addCriterion("telZip like", value, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipNotLike(String value) {
            addCriterion("telZip not like", value, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipIn(List<String> values) {
            addCriterion("telZip in", values, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipNotIn(List<String> values) {
            addCriterion("telZip not in", values, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipBetween(String value1, String value2) {
            addCriterion("telZip between", value1, value2, "telzip");
            return (Criteria) this;
        }

        public Criteria andTelzipNotBetween(String value1, String value2) {
            addCriterion("telZip not between", value1, value2, "telzip");
            return (Criteria) this;
        }

        public Criteria andValidflagIsNull() {
            addCriterion("validFlag is null");
            return (Criteria) this;
        }

        public Criteria andValidflagIsNotNull() {
            addCriterion("validFlag is not null");
            return (Criteria) this;
        }

        public Criteria andValidflagEqualTo(String value) {
            addCriterion("validFlag =", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagNotEqualTo(String value) {
            addCriterion("validFlag <>", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagGreaterThan(String value) {
            addCriterion("validFlag >", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagGreaterThanOrEqualTo(String value) {
            addCriterion("validFlag >=", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagLessThan(String value) {
            addCriterion("validFlag <", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagLessThanOrEqualTo(String value) {
            addCriterion("validFlag <=", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagLike(String value) {
            addCriterion("validFlag like", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagNotLike(String value) {
            addCriterion("validFlag not like", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagIn(List<String> values) {
            addCriterion("validFlag in", values, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagNotIn(List<String> values) {
            addCriterion("validFlag not in", values, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagBetween(String value1, String value2) {
            addCriterion("validFlag between", value1, value2, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagNotBetween(String value1, String value2) {
            addCriterion("validFlag not between", value1, value2, "validflag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table town
     *
     * @mbggenerated do_not_delete_during_merge Thu May 23 11:26:38 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table town
     *
     * @mbggenerated Thu May 23 11:26:38 CST 2013
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

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

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

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

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}