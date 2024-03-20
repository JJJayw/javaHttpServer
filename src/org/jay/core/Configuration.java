package org.jay.core;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.SAXParser;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 存储数据配置信息
public class Configuration {

    public static final int PORT;

    public static final String BASE_URL;


    // 存放数据源的信息
    private static final Map<String, String> DATA_SOURCE = new HashMap<>(8);

    // 放置Servlet的配置信息
    private static final Map<String, String> SERVLETS = new ConcurrentHashMap<>(8);

    public static Map<String, String> getDataSource() {
        return DATA_SOURCE;
    }

    public static Map<String, String> getSERVLETS() {
        return SERVLETS;
    }

    static {
        // 读取文档对象
        URL url = Thread.currentThread().getContextClassLoader().getResource("web.xml");
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(url);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        if (document != null) {
            // 获取根节点
            Element root = document.getRootElement();

            // 遍历数据源
            Element dataSource = root.element("data-source");
            for (Iterator<Element> it = dataSource.elementIterator(); it.hasNext(); ) {
                Element element = it.next();
                Attribute name = element.attribute("name");
                Attribute value = element.attribute("value");
                DATA_SOURCE.put(name.getValue(), value.getValue());
            }

            // 遍历Servlet
            Element servlets = root.element("servlets");
            for (Iterator<Element> it = servlets.elementIterator(); it.hasNext(); ) {
                Element element = it.next();
                Element name = element.element("url");
                Element value = element.element("servlet-class");
                SERVLETS.put(name.getText(), value.getText());
            }

            Element port = root.element("port");
            PORT = Integer.parseInt(port.getText());

            Element baseUrl = root.element("port");
            BASE_URL = baseUrl.getText();
        } else {
            PORT = 8080;
            BASE_URL = "D:/wwww";
        }
    }
}
