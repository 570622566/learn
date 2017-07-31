package test.com.craw;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2017/5/23.
 */
public class CrawUtils {

    /**
     * We're not using any methods so that the source code is as straight
     * forward as possible.
     *
     * No exception handling at all for simplicity
     */
    public static void main(String[] args) throws IOException,
            ParserConfigurationException, XPathExpressionException,
            TransformerException {

        String address = "http://www.cnblogs.com/wanghaomiao/p/4899355.html";
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "info");

        String html;

        {
            // the httpclient part
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(address);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            ContentType contentType = ContentType.getOrDefault(entity);
            Charset charset = contentType.getCharset();
            Charset charset1 = Charset.forName("gb2312");

            BufferedReader r = new BufferedReader(new InputStreamReader(
                    entity.getContent(), charset));

            // we can directly plug the input to HtmlCleaner,
            // but we put it in a string so we can print it,
            // or save it to a file
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = r.readLine()) != null) {
                builder.append(line);
            }
            html = builder.toString();
        }

        // HtmlCleaner part
        TagNode tagNode = new HtmlCleaner().clean(html);
      /*  String cleanHtml = new SimpleHtmlSerializer(new CleanerProperties())
                .getAsString(tagNode);*/
        // we need a DOM document to execute xpath, HtmlCleaner helps in creating one
        org.w3c.dom.Document doc = new DomSerializer(new CleanerProperties()) .createDOM(tagNode);

        // the xpath part
        XPath xpath = XPathFactory.newInstance().newXPath();
        String imgURL = (String) xpath.evaluate("//*[@id=\"cnblogs_post_body\"]/p[1]", doc,
                XPathConstants.STRING);

        //using two URLs we can make sure we get the absolute URL even if relative.
        System.out.println(new URL(new URL(address), imgURL).toString());
    }


}
