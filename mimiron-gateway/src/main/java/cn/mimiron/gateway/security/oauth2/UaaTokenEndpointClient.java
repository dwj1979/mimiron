package cn.mimiron.gateway.security.oauth2;

import cn.mimiron.core.config.MimironProperties;
import cn.mimiron.gateway.config.oauth2.OAuth2Properties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * Client talking to UAA's token endpoint to do different OAuth2 grants.
 *
 * @author zhangxd
 */
@Component
public class UaaTokenEndpointClient extends OAuth2TokenEndpointClientAdapter implements OAuth2TokenEndpointClient {

    public UaaTokenEndpointClient(@Qualifier("loadBalancedRestTemplate") RestTemplate restTemplate,
                                  MimironProperties mimironProperties, OAuth2Properties oAuth2Properties) {
        super(restTemplate, mimironProperties, oAuth2Properties);
    }

    @Override
    protected void addAuthentication(HttpHeaders reqHeaders, MultiValueMap<String, String> formParams) {
        reqHeaders.add("Authorization", getAuthorizationHeader());
    }

    /**
     * @return a Basic authorization header to be used to talk to UAA.
     */
    protected String getAuthorizationHeader() {
        String clientId = getClientId();
        String clientSecret = getClientSecret();
        String authorization = clientId + ":" + clientSecret;
        return "Basic " + Base64Utils.encodeToString(authorization.getBytes(StandardCharsets.UTF_8));
    }

}
