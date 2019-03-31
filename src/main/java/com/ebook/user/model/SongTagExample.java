package com.ebook.user.model;

import java.util.ArrayList;
import java.util.List;

public class SongTagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SongTagExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSongidIsNull() {
            addCriterion("songId is null");
            return (Criteria) this;
        }

        public Criteria andSongidIsNotNull() {
            addCriterion("songId is not null");
            return (Criteria) this;
        }

        public Criteria andSongidEqualTo(String value) {
            addCriterion("songId =", value, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidNotEqualTo(String value) {
            addCriterion("songId <>", value, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidGreaterThan(String value) {
            addCriterion("songId >", value, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidGreaterThanOrEqualTo(String value) {
            addCriterion("songId >=", value, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidLessThan(String value) {
            addCriterion("songId <", value, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidLessThanOrEqualTo(String value) {
            addCriterion("songId <=", value, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidLike(String value) {
            addCriterion("songId like", value, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidNotLike(String value) {
            addCriterion("songId not like", value, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidIn(List<String> values) {
            addCriterion("songId in", values, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidNotIn(List<String> values) {
            addCriterion("songId not in", values, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidBetween(String value1, String value2) {
            addCriterion("songId between", value1, value2, "songid");
            return (Criteria) this;
        }

        public Criteria andSongidNotBetween(String value1, String value2) {
            addCriterion("songId not between", value1, value2, "songid");
            return (Criteria) this;
        }

        public Criteria andPositiveProbIsNull() {
            addCriterion("positive_prob is null");
            return (Criteria) this;
        }

        public Criteria andPositiveProbIsNotNull() {
            addCriterion("positive_prob is not null");
            return (Criteria) this;
        }

        public Criteria andPositiveProbEqualTo(Double value) {
            addCriterion("positive_prob =", value, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbNotEqualTo(Double value) {
            addCriterion("positive_prob <>", value, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbGreaterThan(Double value) {
            addCriterion("positive_prob >", value, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbGreaterThanOrEqualTo(Double value) {
            addCriterion("positive_prob >=", value, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbLessThan(Double value) {
            addCriterion("positive_prob <", value, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbLessThanOrEqualTo(Double value) {
            addCriterion("positive_prob <=", value, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbIn(List<Double> values) {
            addCriterion("positive_prob in", values, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbNotIn(List<Double> values) {
            addCriterion("positive_prob not in", values, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbBetween(Double value1, Double value2) {
            addCriterion("positive_prob between", value1, value2, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andPositiveProbNotBetween(Double value1, Double value2) {
            addCriterion("positive_prob not between", value1, value2, "positiveProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbIsNull() {
            addCriterion("negative_prob is null");
            return (Criteria) this;
        }

        public Criteria andNegativeProbIsNotNull() {
            addCriterion("negative_prob is not null");
            return (Criteria) this;
        }

        public Criteria andNegativeProbEqualTo(Double value) {
            addCriterion("negative_prob =", value, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbNotEqualTo(Double value) {
            addCriterion("negative_prob <>", value, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbGreaterThan(Double value) {
            addCriterion("negative_prob >", value, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbGreaterThanOrEqualTo(Double value) {
            addCriterion("negative_prob >=", value, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbLessThan(Double value) {
            addCriterion("negative_prob <", value, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbLessThanOrEqualTo(Double value) {
            addCriterion("negative_prob <=", value, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbIn(List<Double> values) {
            addCriterion("negative_prob in", values, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbNotIn(List<Double> values) {
            addCriterion("negative_prob not in", values, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbBetween(Double value1, Double value2) {
            addCriterion("negative_prob between", value1, value2, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andNegativeProbNotBetween(Double value1, Double value2) {
            addCriterion("negative_prob not between", value1, value2, "negativeProb");
            return (Criteria) this;
        }

        public Criteria andConfidenceIsNull() {
            addCriterion("confidence is null");
            return (Criteria) this;
        }

        public Criteria andConfidenceIsNotNull() {
            addCriterion("confidence is not null");
            return (Criteria) this;
        }

        public Criteria andConfidenceEqualTo(Double value) {
            addCriterion("confidence =", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotEqualTo(Double value) {
            addCriterion("confidence <>", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceGreaterThan(Double value) {
            addCriterion("confidence >", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceGreaterThanOrEqualTo(Double value) {
            addCriterion("confidence >=", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceLessThan(Double value) {
            addCriterion("confidence <", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceLessThanOrEqualTo(Double value) {
            addCriterion("confidence <=", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceIn(List<Double> values) {
            addCriterion("confidence in", values, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotIn(List<Double> values) {
            addCriterion("confidence not in", values, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceBetween(Double value1, Double value2) {
            addCriterion("confidence between", value1, value2, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotBetween(Double value1, Double value2) {
            addCriterion("confidence not between", value1, value2, "confidence");
            return (Criteria) this;
        }

        public Criteria andSentimentIsNull() {
            addCriterion("sentiment is null");
            return (Criteria) this;
        }

        public Criteria andSentimentIsNotNull() {
            addCriterion("sentiment is not null");
            return (Criteria) this;
        }

        public Criteria andSentimentEqualTo(Integer value) {
            addCriterion("sentiment =", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentNotEqualTo(Integer value) {
            addCriterion("sentiment <>", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentGreaterThan(Integer value) {
            addCriterion("sentiment >", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentGreaterThanOrEqualTo(Integer value) {
            addCriterion("sentiment >=", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentLessThan(Integer value) {
            addCriterion("sentiment <", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentLessThanOrEqualTo(Integer value) {
            addCriterion("sentiment <=", value, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentIn(List<Integer> values) {
            addCriterion("sentiment in", values, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentNotIn(List<Integer> values) {
            addCriterion("sentiment not in", values, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentBetween(Integer value1, Integer value2) {
            addCriterion("sentiment between", value1, value2, "sentiment");
            return (Criteria) this;
        }

        public Criteria andSentimentNotBetween(Integer value1, Integer value2) {
            addCriterion("sentiment not between", value1, value2, "sentiment");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andHotIsNull() {
            addCriterion("hot is null");
            return (Criteria) this;
        }

        public Criteria andHotIsNotNull() {
            addCriterion("hot is not null");
            return (Criteria) this;
        }

        public Criteria andHotEqualTo(Integer value) {
            addCriterion("hot =", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotNotEqualTo(Integer value) {
            addCriterion("hot <>", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotGreaterThan(Integer value) {
            addCriterion("hot >", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotGreaterThanOrEqualTo(Integer value) {
            addCriterion("hot >=", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotLessThan(Integer value) {
            addCriterion("hot <", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotLessThanOrEqualTo(Integer value) {
            addCriterion("hot <=", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotIn(List<Integer> values) {
            addCriterion("hot in", values, "hot");
            return (Criteria) this;
        }

        public Criteria andHotNotIn(List<Integer> values) {
            addCriterion("hot not in", values, "hot");
            return (Criteria) this;
        }

        public Criteria andHotBetween(Integer value1, Integer value2) {
            addCriterion("hot between", value1, value2, "hot");
            return (Criteria) this;
        }

        public Criteria andHotNotBetween(Integer value1, Integer value2) {
            addCriterion("hot not between", value1, value2, "hot");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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