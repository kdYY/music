package com.ebook.user.model;

import java.util.ArrayList;
import java.util.List;

public class SonglistExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SonglistExample() {
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

        public Criteria andSonglistidIsNull() {
            addCriterion("songListId is null");
            return (Criteria) this;
        }

        public Criteria andSonglistidIsNotNull() {
            addCriterion("songListId is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistidEqualTo(String value) {
            addCriterion("songListId =", value, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidNotEqualTo(String value) {
            addCriterion("songListId <>", value, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidGreaterThan(String value) {
            addCriterion("songListId >", value, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidGreaterThanOrEqualTo(String value) {
            addCriterion("songListId >=", value, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidLessThan(String value) {
            addCriterion("songListId <", value, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidLessThanOrEqualTo(String value) {
            addCriterion("songListId <=", value, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidLike(String value) {
            addCriterion("songListId like", value, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidNotLike(String value) {
            addCriterion("songListId not like", value, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidIn(List<String> values) {
            addCriterion("songListId in", values, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidNotIn(List<String> values) {
            addCriterion("songListId not in", values, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidBetween(String value1, String value2) {
            addCriterion("songListId between", value1, value2, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistidNotBetween(String value1, String value2) {
            addCriterion("songListId not between", value1, value2, "songlistid");
            return (Criteria) this;
        }

        public Criteria andSonglistnameIsNull() {
            addCriterion("songListName is null");
            return (Criteria) this;
        }

        public Criteria andSonglistnameIsNotNull() {
            addCriterion("songListName is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistnameEqualTo(String value) {
            addCriterion("songListName =", value, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameNotEqualTo(String value) {
            addCriterion("songListName <>", value, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameGreaterThan(String value) {
            addCriterion("songListName >", value, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameGreaterThanOrEqualTo(String value) {
            addCriterion("songListName >=", value, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameLessThan(String value) {
            addCriterion("songListName <", value, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameLessThanOrEqualTo(String value) {
            addCriterion("songListName <=", value, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameLike(String value) {
            addCriterion("songListName like", value, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameNotLike(String value) {
            addCriterion("songListName not like", value, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameIn(List<String> values) {
            addCriterion("songListName in", values, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameNotIn(List<String> values) {
            addCriterion("songListName not in", values, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameBetween(String value1, String value2) {
            addCriterion("songListName between", value1, value2, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistnameNotBetween(String value1, String value2) {
            addCriterion("songListName not between", value1, value2, "songlistname");
            return (Criteria) this;
        }

        public Criteria andSonglistpicIsNull() {
            addCriterion("songListPic is null");
            return (Criteria) this;
        }

        public Criteria andSonglistpicIsNotNull() {
            addCriterion("songListPic is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistpicEqualTo(String value) {
            addCriterion("songListPic =", value, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicNotEqualTo(String value) {
            addCriterion("songListPic <>", value, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicGreaterThan(String value) {
            addCriterion("songListPic >", value, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicGreaterThanOrEqualTo(String value) {
            addCriterion("songListPic >=", value, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicLessThan(String value) {
            addCriterion("songListPic <", value, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicLessThanOrEqualTo(String value) {
            addCriterion("songListPic <=", value, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicLike(String value) {
            addCriterion("songListPic like", value, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicNotLike(String value) {
            addCriterion("songListPic not like", value, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicIn(List<String> values) {
            addCriterion("songListPic in", values, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicNotIn(List<String> values) {
            addCriterion("songListPic not in", values, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicBetween(String value1, String value2) {
            addCriterion("songListPic between", value1, value2, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistpicNotBetween(String value1, String value2) {
            addCriterion("songListPic not between", value1, value2, "songlistpic");
            return (Criteria) this;
        }

        public Criteria andSonglistcountIsNull() {
            addCriterion("songListCount is null");
            return (Criteria) this;
        }

        public Criteria andSonglistcountIsNotNull() {
            addCriterion("songListCount is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistcountEqualTo(Integer value) {
            addCriterion("songListCount =", value, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountNotEqualTo(Integer value) {
            addCriterion("songListCount <>", value, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountGreaterThan(Integer value) {
            addCriterion("songListCount >", value, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("songListCount >=", value, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountLessThan(Integer value) {
            addCriterion("songListCount <", value, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountLessThanOrEqualTo(Integer value) {
            addCriterion("songListCount <=", value, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountIn(List<Integer> values) {
            addCriterion("songListCount in", values, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountNotIn(List<Integer> values) {
            addCriterion("songListCount not in", values, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountBetween(Integer value1, Integer value2) {
            addCriterion("songListCount between", value1, value2, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistcountNotBetween(Integer value1, Integer value2) {
            addCriterion("songListCount not between", value1, value2, "songlistcount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountIsNull() {
            addCriterion("songListPlayCount is null");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountIsNotNull() {
            addCriterion("songListPlayCount is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountEqualTo(Long value) {
            addCriterion("songListPlayCount =", value, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountNotEqualTo(Long value) {
            addCriterion("songListPlayCount <>", value, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountGreaterThan(Long value) {
            addCriterion("songListPlayCount >", value, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountGreaterThanOrEqualTo(Long value) {
            addCriterion("songListPlayCount >=", value, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountLessThan(Long value) {
            addCriterion("songListPlayCount <", value, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountLessThanOrEqualTo(Long value) {
            addCriterion("songListPlayCount <=", value, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountIn(List<Long> values) {
            addCriterion("songListPlayCount in", values, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountNotIn(List<Long> values) {
            addCriterion("songListPlayCount not in", values, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountBetween(Long value1, Long value2) {
            addCriterion("songListPlayCount between", value1, value2, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andSonglistplaycountNotBetween(Long value1, Long value2) {
            addCriterion("songListPlayCount not between", value1, value2, "songlistplaycount");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueIsNull() {
            addCriterion("emotionValue is null");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueIsNotNull() {
            addCriterion("emotionValue is not null");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueEqualTo(Integer value) {
            addCriterion("emotionValue =", value, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueNotEqualTo(Integer value) {
            addCriterion("emotionValue <>", value, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueGreaterThan(Integer value) {
            addCriterion("emotionValue >", value, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueGreaterThanOrEqualTo(Integer value) {
            addCriterion("emotionValue >=", value, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueLessThan(Integer value) {
            addCriterion("emotionValue <", value, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueLessThanOrEqualTo(Integer value) {
            addCriterion("emotionValue <=", value, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueIn(List<Integer> values) {
            addCriterion("emotionValue in", values, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueNotIn(List<Integer> values) {
            addCriterion("emotionValue not in", values, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueBetween(Integer value1, Integer value2) {
            addCriterion("emotionValue between", value1, value2, "emotionvalue");
            return (Criteria) this;
        }

        public Criteria andEmotionvalueNotBetween(Integer value1, Integer value2) {
            addCriterion("emotionValue not between", value1, value2, "emotionvalue");
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