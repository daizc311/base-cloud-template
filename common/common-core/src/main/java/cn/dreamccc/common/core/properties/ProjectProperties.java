package cn.dreamccc.common.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("project")
public class ProjectProperties {

    /**
     * 系统版本号
     */
    private String version;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 系统描述
     */
    private String description;
}
