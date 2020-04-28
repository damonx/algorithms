package com.damonx.util;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

public class HttpClientBuilder
{

    private CloseableHttpClient httpClient;

    private final int connectionRequestTimeout = 5000;
    private int socketTimeout = 30000;
    private int connectTimeout = 5000;
    private final int maxTotal = 500;
    private final int maxPerRoute = 100;

    private final String detailHostName = "http://www.google.com";
    private final int detailPort = 80;
    private final int detailMaxPerRoute = 100;

    public HttpClientBuilder withConnectTimeout(final int connTimeout)
    {
        connectTimeout = connTimeout;
        return this;
    }

    public HttpClientBuilder withSocketTimeout(final int socTimeOut)
    {
        socketTimeout = socTimeOut;
        return this;
    }

    public CloseableHttpClient build()
    {
        if (null == httpClient) {
            synchronized (HttpClientBuilder.class) {
                if (null == httpClient) {
                    httpClient = init();
                }
            }
        }
        return httpClient;
    }

    public CloseableHttpClient init()
    {
        final ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        final LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf)
            .register("https", sslsf).build();
        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(maxPerRoute);

        final HttpHost httpHost = new HttpHost(detailHostName, detailPort);
        cm.setMaxPerRoute(new HttpRoute(httpHost), detailMaxPerRoute);
        final HttpRequestRetryHandler httpRequestRetryHandler = (final IOException exception, final int executionCount,
                final HttpContext context) -> {
            if (executionCount >= 2) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                // connection lost
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // time out
                return false;
            }
            if (exception instanceof UnknownHostException) {
                return false;
            }
            if (exception instanceof ConnectionPoolTimeoutException) {
                return false;
            }
            if (exception instanceof SSLException) {
                return false;
            }
            final HttpClientContext clientContext = HttpClientContext.adapt(context);
            final HttpRequest request = clientContext.getRequest();

            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }

            return false;
        };

        final RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
            .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        return HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig)
            .setRetryHandler(httpRequestRetryHandler).build();
    }
}
