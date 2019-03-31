package com.ebook.user.dao;

import com.ebook.user.model.Taginsonglist;
import com.ebook.user.model.TaginsonglistExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface TaginsonglistMapper {
    int countByExample(TaginsonglistExample example);

    int deleteByExample(TaginsonglistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Taginsonglist record);

    int insertSelective(Taginsonglist record);

    List<Taginsonglist> selectByExample(TaginsonglistExample example);

    Taginsonglist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Taginsonglist record, @Param("example") TaginsonglistExample example);

    int updateByExample(@Param("record") Taginsonglist record, @Param("example") TaginsonglistExample example);

    int updateByPrimaryKeySelective(Taginsonglist record);

    int updateByPrimaryKey(Taginsonglist record);

    void insertBatch(List<Taginsonglist> list);

    List<Taginsonglist> selectPageByTagId(int tagId);
}