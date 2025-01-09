package betta.service;


import com.alibaba.fastjson.JSON;
import betta.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RestService {

    private static final String LOGIN_URL = "/login";

    @Value("${corn.apiUrl}")
    String apiUrl;

    String token = "";

    @Autowired
    private RestTemplate restTemplate;

    public void login() {
        Map param = new HashMap(2);
//        param.put("username", BtoaEncode.encryption("damu"));
//        param.put("password", BtoaEncode.encryption("shmily@123"));
        param.put("username", "damu");
        param.put("password", "shmily@123");
        AjaxResult post = post(LOGIN_URL, param);
        token = (String) post.get("token");
    }

    public AjaxResult get(String uri, Map<String, ?> param) {
        return restApi(parseUrl(uri, param), null, HttpMethod.GET);
    }

    public AjaxResult uploadFile(String uri, File file) {
        // 创建MultiValueMap对象
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        FileSystemResource fileResource = new FileSystemResource(file);
        body.add("file", fileResource);

        // 创建HttpHeaders对象
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 发送POST请求
        return restApi(uri, body, HttpMethod.POST, headers);


    }

    public <R> AjaxResult post(String uri, R body) {
        return restApi(uri, body, HttpMethod.POST);
    }

    public <R> AjaxResult put(String uri, R body) {
        return restApi(uri, body, HttpMethod.PUT);
    }

    public <R> AjaxResult delete(String uri) {
        return restApi(uri, null, HttpMethod.DELETE);
    }

    private <R> AjaxResult restApi(String uri, R body, HttpMethod httpMethod) {
        return restApi(uri, body, httpMethod, new HttpHeaders());
    }

    private <R> AjaxResult restApi(String uri, R body, HttpMethod httpMethod, HttpHeaders headers) {

        //不是登录URL要设置token
        if (!LOGIN_URL.equals(uri)) {
            if (StringUtils.isBlank(token)) {
                login();
            }
            headers.set("Authorization", "Bearer " + token);
        }

        HttpEntity<R> requestEntity = new HttpEntity(body, headers);

        String url = apiUrl + uri;
        log.info("Request url: [{}], method: {}, RequestBody: {}, RequestHeader: {} ", url, httpMethod.name(),
                body instanceof LinkedMultiValueMap ? "file" : JSON.toJSONString(body), requestEntity.getHeaders());

        AjaxResult result;
        if (httpMethod.matches("GET")) {
            //url已编码的请求调用该方法，直接传递URI,以避免restTemplate的再次编码
            result = restTemplate.exchange(URI.create(url), httpMethod, requestEntity, new ParameterizedTypeReference<AjaxResult>() {
            }).getBody();
        } else {
            result = restTemplate.exchange(url, httpMethod, requestEntity, new ParameterizedTypeReference<AjaxResult>() {
            }).getBody();
        }
        log.info("result: {}", result.toString());
        return result;
    }

    public String parseUrl(String url, Map<String, ?> params) {
        if (null == params || params.size() <= 0) {
            return url;
        }
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, ?> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return url + "?" + URLEncodedUtils.format(pairs, "UTF-8");
    }

}
