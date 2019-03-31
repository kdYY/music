package com.ebook.user.service;

import com.ebook.user.dao.TaginsonglistMapper;
import com.ebook.user.model.Taginsonglist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagSongListService {

    @Autowired
    private TaginsonglistMapper mapper;

    public void insertBatch(List<Taginsonglist> list) {
        mapper.insertBatch(list);
    }


}
