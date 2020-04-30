package com.yiyjm.nest.service;

import com.yiyjm.nest.dao.ImageDao;
import com.yiyjm.nest.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImageService {
    private ImageDao imageDao;

    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public List<Image> list(Integer iid, Integer per) {
        if (iid==null || iid<=0) {
            iid = Integer.MAX_VALUE;
        }
        if (per==null || per > 16) {
            per = 16;
        }
        return imageDao.listUploadImage(iid, per);
    }

}
