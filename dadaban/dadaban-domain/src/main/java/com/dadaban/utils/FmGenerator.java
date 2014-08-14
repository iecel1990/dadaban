package com.dadaban.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jrose on 8/6/14.
 */
public class FmGenerator {
    public static void main(String[] args){


        Map<String,Object> root = new HashMap<String, Object>();
        root.put("UpBeanName", "Files");
        root.put("beanName", "files");

//        generator(root, "/service.ftl");
        generator(root, "/controller.ftl");
    }


    private static void generator(Map<String, Object> root, String ftl) {
        try {
            Configuration cfg = new Configuration();
            String path = FmGenerator.class.getResource("/").getPath()+"template";

            cfg.setDirectoryForTemplateLoading(new File(path));
            Template template = cfg.getTemplate(ftl);
            StringWriter out = new StringWriter();
            template.process(root, out);
            System.out.println(out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }
    }
}
