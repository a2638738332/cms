package com.briup.cms;

import com.briup.cms.dao.SlideshowDao;
import com.briup.cms.domain.Slideshow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CmsApplicationTests {
    @Autowired
    SlideshowDao slideshowDao;
    @Test
    void contextLoads() {
        List<Slideshow> slideshows = slideshowDao.selectList(null);
        System.out.println(slideshows);
    }

}
