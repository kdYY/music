package com.ebook.user.service;

import com.ebook.user.dao.TagMapper;
import com.ebook.user.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private TagMapper mapper;

    public int insert(Tag tag) {
        return mapper.insert(tag);
    }
}
