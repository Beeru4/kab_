package com.beerus.uitls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author Beerus
 * @Description 更改属性文件编码格式
 * @Date 2019-07-04
 **/
public class PropertiesHandler implements PropertySourceLoader {
  private Logger logger = LoggerFactory.getLogger(PropertiesHandler.class);

  @Override
  public String[] getFileExtensions() {
    return new String[]{"properties", "xml"};
  }

  @Override
  public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
    Map<String, ?> properties = loadProperties(resource);
    if (properties.isEmpty()) {
      return Collections.emptyList();
    }
    return Collections
      .singletonList(new OriginTrackedMapPropertySource(name, properties));
  }

  private Map<String, ?> loadProperties(Resource resource) {
    Properties properties = new Properties();
    InputStream inputStream = null;
    try {
      inputStream = resource.getInputStream();
      properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
      inputStream.close();
    } catch (IOException e) {
      logger.error("load inputstream failure...", e);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          logger.error("close IO failure ....", e);
        }
      }
    }
    return (Map) properties;
  }

}
