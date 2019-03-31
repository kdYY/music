package com.ebook.user.dao;

import com.ebook.user.model.UserSongSheet;
import com.ebook.user.model.UserSongSheetExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserSongSheetMapper {
    int countByExample(UserSongSheetExample example);

    int deleteByExample(UserSongSheetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserSongSheet record);

    int insertSelective(UserSongSheet record);

    List<UserSongSheet> selectByExample(UserSongSheetExample example);

    UserSongSheet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserSongSheet record, @Param("example") UserSongSheetExample example);

    int updateByExample(@Param("record") UserSongSheet record, @Param("example") UserSongSheetExample example);

    int updateByPrimaryKeySelective(UserSongSheet record);

    int updateByPrimaryKey(UserSongSheet record);
}