package com.dj.scaffold.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxUtil {

    private static final String IMG_PATTERN =
            "<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>";

    /**
     * 从HTML源码中提取图片路径，最后以一个 String 类型的 List返回，如果不包含任何图片，则返回一个 size=0的List 需要注意的是，此方法只会提取以下格式的图片：
     * .jpg|.bmp|.eps|.gif|.mif|.miff|.png|.tif| .tiff|.svg|.wmf|.jpe|.jpeg|.dib|.ico|.tga|.cut|.pic
     */
    public static List<String> getImageSrc(String htmlCode) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile(IMG_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0]
                    : m.group(2);
            imageSrcList.add(src);
        }
        return imageSrcList;
    }


    // 从html中提取纯文本
    public static String getTextContent(String strHtml) {
        // 剔出<html>的标签
        String txtcontent = strHtml.replaceAll("</?[^>]+>", "");
        // 去除字符串中的空格,回车,换行符,制表符
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");
        return txtcontent;
    }


    public static void main(String[] args) {
        String sss =
                "<p><span style=\"background-color: white; color: rgb(26, 26, 26);\">留学两年，两年没回。以后还要上大学求学位，粗略算了一下少说10年回不了家过年吧。 两年不算久，先马着，以后回来答。 只是想想那句话就说不出话：过年了，在外的游子不论如何都是要回家的，有家的地方才叫过年。</span></p>";
       /* List<String> imageSrc = getImageSrc(sss);
        for (String s : imageSrc) {
            System.out.println(s);
        }*/

        /*String textContent =getTextContent(sss);
        System.out.println(textContent);
        if (StringUtils.isNotBlank(textContent)) {
            System.out.println(textContent.length());
            if (textContent.length() > 100) {
                textContent = textContent.substring(0, 100) + "...";
                System.out.println(textContent);
            }

        }*/
    }

}

