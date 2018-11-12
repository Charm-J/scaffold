package com.dj.scaffold;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ApiDocTest {

    /**
     * 简单型接口，不需要指定请求头，并且项目是maven的.
     */
    @Test
    public void testBuilderControllersApiSimple() {
        //将生成的文档输出到d:\md目录下，严格模式下api-doc会检测Controller的接口注释
        ApiDocBuilder.builderControllersApi("d:\\md", true);

    }

    /**
     * 包括设置请求头，缺失注释的字段批量在文档生成期使用定义好的注释
     */
    @Test
    public void testBuilderControllersApi() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://localhost:9999");
        config.setStrict(true);//true会严格要求注释，推荐设置true
        config.setAllInOne(true);//true会将文档合并导出到一个markdown
        config.setOutPath("d:\\md");
        //不指定SourcePaths默认加载代码为项目src/main/java下的,如果项目的某一些实体来自外部代码可以一起加载
        config.setSourcePaths(
                SourcePath.path().setDesc("本项目代码").setPath("src/main/java")
        );
        long start = System.currentTimeMillis();
        ApiDocBuilder.builderControllersApi(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }


}
