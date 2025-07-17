package xyz.ddlnt.serviceuser.conf.configProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/16 22:10
 */
@Data
@Component
@ConfigurationProperties(prefix = "oauth2.github")
public class Oauth2ConfigProperties {
    private String clientId;
    private String clientSecret;
    private String successUrl;
}
