package org.docksidestage.app.web.wx.request.json;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.docksidestage.unit.UnitFortressWebTestCase;

/**
 * @author jflute
 */
public class WxRequestJsonBodyActionTest extends UnitFortressWebTestCase {

    public void test_request_index() {
        String url = "http://localhost:8151/fortress/wx/request/json/body/";
        requestJsonBody(url, "{sea: \"mystic\"}");
    }

    public void test_request_clienterror_integer() {
        String url = "http://localhost:8151/fortress/wx/request/json/body/clienterror/";
        requestJsonBody(url, "{land: \"415\"}");
    }

    public void test_request_clienterror_cdef() {
        String url = "http://localhost:8151/fortress/wx/request/json/body/clienterror/";
        requestJsonBody(url, "{dstore: \"detarame\"}");
    }

    public void test_request_systemerror() {
        String url = "http://localhost:8151/fortress/wx/request/json/body/systemerror/";
        requestJsonBody(url, "{sea: \"mystic\"\n, piari: \"2017-04-14\"}");
    }

    protected void requestJsonBody(String url, String json) {
        log("...Requesting {}", url);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json);
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(entity);
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                return;
            }
        } catch (IOException ignored) { // because of tool
        }
    }
}
