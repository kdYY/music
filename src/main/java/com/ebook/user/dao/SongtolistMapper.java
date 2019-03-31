package com.ebook.user.dao;

import com.ebook.user.model.Songtolist;
import com.ebook.user.model.SongtolistExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SongtolistMapper {
    int countByExample(SongtolistExample example);

    int deleteByExample(SongtolistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Songtolist record);

    int insertSelective(Songtolist record);

    List<Songtolist> selectByExample(SongtolistExample example);

    Songtolist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Songtolist record, @Param("example") SongtolistExample example);

    int updateByExample(@Param("record") Songtolist record, @Param("example") SongtolistExample example);

    int updateByPrimaryKeySelective(Songtolist record);

    int updateByPrimaryKey(Songtolist record);

    void insertBatch(List<Songtolist> songtolists);
}