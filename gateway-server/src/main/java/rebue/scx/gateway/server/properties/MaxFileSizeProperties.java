package rebue.scx.gateway.server.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "scx.gateway.multipart.max-file-size")
public class MaxFileSizeProperties {
    /**
     * 默认限制最大上传文件大小(默认1MB)
     */
    private DataSize      defaultLimit = DataSize.ofMegabytes(1);
    /**
     * 要进行过滤的链接
     */
    private List<Specify> specifies;

    @Data
    public static class Specify {
        private String   url;
        private DataSize limit;
    }
}
