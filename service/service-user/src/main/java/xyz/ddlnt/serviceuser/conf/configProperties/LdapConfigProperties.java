package xyz.ddlnt.serviceuser.conf.configProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/9 19:17
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.ldap")
public class LdapConfigProperties {
    private String urls;
    private String base;
    private String username;
    private String password;
}
