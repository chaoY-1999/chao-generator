import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setNumberFormat("0.######");

        Template template = configuration.getTemplate("myweb.html.ftl");
        HashMap<String, Object> dataModel = getStringObjectHashMap();
        Writer out = new FileWriter("myweb.html");
        template.process(dataModel, out);

        out.close();
    }

    private static HashMap<String, Object> getStringObjectHashMap() {
        HashMap<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2024);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        HashMap<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://github.com/chaoY-1999");
        menuItem1.put("label", "github主页");
        HashMap<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://github.com/chaoY-1999/chao-generator/");
        menuItem2.put("label", "代码生成器");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems", menuItems);
        return dataModel;
    }

}
