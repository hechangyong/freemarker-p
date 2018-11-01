package com.hecy.freemarkerp.common;

import freemarker.ext.dom.NodeModel;
import freemarker.template.*;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hecy
 * @Date: 2018/11/1 14:12
 * @Version 1.0
 */
public class Generator {

    private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

    public static void main(String[] args) throws IOException {

        File file = ResourceUtils.getFile("classpath:temple/helloworld.ftl");
        System.out.println(file.getName());
//        if (file.exists()) {
//            File[] files = file.listFiles();
//            if (files != null) {
//                for (File childFile : files) {
//                    System.out.println(childFile.getName());
//                }
//            }
//        }
        String inputxml = "classpath:inputxml\\test.xml";
        String inputftl = "D:\\mygit\\freemarker-p\\src\\main\\resources\\temple\\";
        String outputftl = "D:\\mygit\\freemarker-p\\src\\main\\resources\\outputFile\\sss.html";

        String templateName = "helloworld.ftl";

        genFile(templateName, inputxml, outputftl);

    }

    public static void genFile(String templateName, String inputName, String outFileName) throws IOException {


        //指定模板文件的来源
        cfg.setDirectoryForTemplateLoading( ResourceUtils.getFile("classpath:temple"));

        cfg.setDefaultEncoding("UTF-8");

        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        try {
            Template template = cfg.getTemplate(templateName);
            Map<String, Object> root = new HashMap<>();

            NodeModel xml = NodeModel.parse( ResourceUtils.getFile(inputName));
            root.put("doc", xml);
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(outFileName));
            template.process(root, out);
            out.flush();
            out.close();

        } catch (IOException | TemplateException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

    }


}
