package xyz.guqing.creek.security.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author guqing
 * @date 2019-12-22 24:53
 */
@Data
@Component
@ConfigurationProperties("secure")
public class SecurityProperties {
    private final TokenProperties tokenProperties;
    private final LoginProperties loginProperties;

    @Autowired
    public SecurityProperties(LoginProperties loginProperties,
                              TokenProperties tokenProperties) {
        this.loginProperties = loginProperties;
        this.tokenProperties = tokenProperties;
    }
}
