package com.dj.scaffold.util;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ImgUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImgUtil.class);

    /**
     * 返回压缩图片路径
     */
    public static void thumbnailatorImage(String imageUrl, String resultUrl) {
        try {
            Thumbnails.of(imageUrl)
                    .scale(1f)
                    .outputQuality(0.8f)
                    .outputFormat("jpg")
                    .toFile(resultUrl);
        } catch (IOException e) {
            LOGGER.error("压缩图片异常", e);
        }
    }
}
