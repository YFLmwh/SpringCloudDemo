package xyz.ddlnt.serviceuser.conf;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import xyz.ddlnt.serviceuser.conf.configProperties.LdapConfigProperties;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/9 19:31
 */
@Configuration
public class LdapConfiguration {
    @Bean
    public LdapTemplate ldapTemplate(LdapConfigProperties ldapConfigProperties) {
        LdapContextSource source = new LdapContextSource();
        source.setBase(ldapConfigProperties.getBase());  // 该设置将作为后续基路径，类似baseURL，搜索时将自带该路径
        source.setUrl(ldapConfigProperties.getUrls());
        source.setUserDn(ldapConfigProperties.getUsername());
        source.setPassword(ldapConfigProperties.getPassword());
        source.afterPropertiesSet();
        return new LdapTemplate(source);
    }
}
