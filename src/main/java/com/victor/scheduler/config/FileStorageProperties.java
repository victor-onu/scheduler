package com.victor.scheduler.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
@Data
public class FileStorageProperties {
    private String uploadDir;
    private String uploadDirTmp;
}
