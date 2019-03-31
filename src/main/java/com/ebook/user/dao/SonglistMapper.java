package com.ebook.user.dao;

import com.ebook.user.model.Songlist;
import com.ebook.user.model.SonglistExample;
import com.ebook.user.model.SonglistWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SonglistMapper {
    int countByExample(SonglistExample example);

    int deleteByExample(SonglistExample example);

    int deleteByPrimaryKey(String songlistid);

    int insert(SonglistWithBLOBs record);

    int insertSelective(SonglistWithBLOBs record);

    List<SonglistWithBLOBs> selectByExampleWithBLOBs(SonglistExample example);

    List<Songlist> selectByExample(SonglistExample example);

    SonglistWithBLOBs selectByPrimaryKey(String songlistid);

    int updateByExampleSelective(@Param("record") SonglistWithBLOBs record, @Param("example") SonglistExample example);

    int updateByExampleWithBLOBs(@Param("record") SonglistWithBLOBs record, @Param("example") SonglistExample example);

    int updateByExample(@Param("record") Songlist record, @Param("example") SonglistExample example);

    int updateByPrimaryKeySelective(SonglistWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SonglistWithBLOBs record);

    int updateByPrimaryKey(Songlist record);

    /**
     * 获取听取歌曲时间最多的
     * @return
     */
    List<Songlist> selectGroupByPlayCount();

    void updateBatch(List<SonglistWithBLOBs> songlistWithBLOBs);

    /**
     * 按照playCount和hot降序排序
     * @param tagName
     * @return
     */
    List<SonglistWithBLOBs> selectByTagName(List<String> tagName);
}