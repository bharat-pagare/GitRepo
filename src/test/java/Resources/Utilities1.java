package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utilities1 {
    public static RequestSpecification req;
        public RequestSpecification Utilis() throws IOException {
            //adding if block to avoid executing below steps building logging file in case of multiple data
            if (req==null) {
                //Building Request Spec for common elements such as Base URI, content type
                PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
                req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).setContentType("application/json")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
                return req;
            }
            return req;
        }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\1000061169\\IdeaProjects\\APIFramework\\src\\test\\java\\Resources\\global.properties");
    prop.load(fis);
    return prop.getProperty(key);
        }
}
