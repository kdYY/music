package com.ebook.user.dao;

import com.ebook.user.model.SongTag;
import com.ebook.user.model.SongTagExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SongTagMapper {
    int countByExample(SongTagExample example);

    int deleteByExample(SongTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SongTag record);

    int insertSelective(SongTag record);

    List<SongTag> selectByExample(SongTagExample example);

    SongTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SongTag record, @Param("example") SongTagExample example);

    int updateByExample(@Param("record") SongTag record, @Param("example") SongTagExample example);

    int updateByPrimaryKeySelective(SongTag record);

    int updateByPrimaryKey(SongTag record);
    void updateBatch(List<SongTag> songTags);
    void insertBatch(List<SongTag> songTags);

    List<SongTag> selectBySongIds(List<String> ids);
}