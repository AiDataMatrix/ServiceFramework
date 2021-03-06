package net.csdn.modules.transport;

import net.csdn.common.collect.Tuple;
import net.csdn.common.path.Url;
import net.csdn.modules.http.RestRequest;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * BlogInfo: WilliamZhu
 * Date: 12-5-29
 * Time: 下午5:09
 */
public interface HttpTransportService {

    public SResponse post(Url url, Map data);

    public SResponse post(Url url, Map data, Map<String, String> headers);

    public SResponse post(final Url url, final Map data, final int timeout);

    public SResponse post(final Url url, final Map data, final Map<String, String> headers, final int timeout);

    public SResponse get(final Url url, final int timeout);

    public SResponse get(Url url);

    public SResponse get(Url url, Map<String, String> data);

    public SResponse get(final Url url, final Map<String, String> data, final int timeout);

    public SResponse put(Url url, Map data);

    public SResponse put(Url url, Map data, Map<String, String> headers);

    public SResponse http(Url url, String jsonData, RestRequest.Method method);

    public SResponse http(Url url, String jsonData, Map<String, String> headers, RestRequest.Method method);

    public SResponse http(Url url, String jsonData, RestRequest.Method method, int timeout);

    public SResponse http(Url url, String jsonData, Map<String, String> headers, RestRequest.Method method, int timeout);

    public FutureTask<SResponse> asyncHttp(final Url url, final String jsonData, RestRequest.Method method);

    public void header(String header, String value);

    public List<SResponse> asyncHttps(final List<Url> urls, final String jsonData, RestRequest.Method method);

    /*
        该函数会确保每个URL 请求都会有Tuple<Url,SResponse> 返回结果。timeout为毫秒数。
        如果URL请求超时或者无法连接，则SResponse会返回503状态码，并且内容为异常信息。
     */
    public List<Tuple<Url,SResponse>> asyncHttps(Map<Url, String> urlWithPostString, final RestRequest.Method method, int timeout);

    class SResponse {
        private int status = 200;
        private String content;


        private Url url;

        public SResponse(int status, String content, Url url) {
            this.status = status;
            this.content = content;
            this.url = url;
        }


        public JSONObject json() {
            return JSONObject.fromObject(content);
        }

        //


        public Url getUrl() {
            return url;
        }

        public void setUrl(Url url) {
            this.url = url;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
