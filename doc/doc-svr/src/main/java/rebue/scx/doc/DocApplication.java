package rebue.scx.doc;

import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.map.HashedMap;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

/**
 * 生成数据库表结构文档
 * 
 * @author yuanman
 *
 */
public class DocApplication {

    /**
     * 生成文件的保存路径
     */
    static String              SAVE_FILE_PATH = "/home/yuanman/myapps";
    /**
     * 生成的文件类型
     */
    static EngineFileType      FILE_TYPE      = EngineFileType.HTML;
    /**
     * 筛选数据源配置文件dev或者prod
     */
    static String              DEV_OR_PROD    = "dev";
    /**
     * 文件路径/bootstrap-dev.yml
     * 文件路径/bootstrap-prod.yml
     * Map<生成文档的文件名, 读取数据源的文件路径>
     */
    static Map<String, String> map            = new HashedMap();
    static {
        map.put("oap数据库", "/home/yuanman/git/scx/oap/oap-svr/src/main/resources/config/");
        map.put("oss数据库", "/home/yuanman/git/scx/oss/oss-svr/src/main/resources/config/");
        map.put("rac数据库", "/home/yuanman/git/scx/rac/rac-svr/src/main/resources/config/");
        map.put("rrl数据库", "/home/yuanman/git/scx/rrl/rrl-svr/src/main/resources/config/");
    }
    // 下列不建议修改
    /**
     * 数据源
     */
    static String   DATESOURCE_URL;
    static String   DATESOURCE_USER_NAME;
    static String   DATESOURCE_PASS_WORD;
    /**
     * 读取数据源的Key
     */
    static String[] fields = new String[3];
    static {
        fields[0] = "spring.datasource.url";
        fields[1] = "spring.datasource.username";
        fields[2] = "spring.datasource.password";
    }
    /**
     * 生成文档的文件名
     */
    static String FILE_NAME;

    public static void main(final String[] args) {
        for (Map.Entry<String, String> map : map.entrySet()) {
            Map<String, String> properties = YmlProperties.getProperties(map.getValue(), DEV_OR_PROD, fields);
            // 设置文件名
            FILE_NAME = map.getKey();
            // 设置数据源
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                if (entry.getKey().equals(fields[0])) {
                    DATESOURCE_URL = entry.getValue();
                }
                if (entry.getKey().equals(fields[1])) {
                    DATESOURCE_USER_NAME = entry.getValue();
                }
                if (entry.getKey().equals(fields[2])) {
                    DATESOURCE_PASS_WORD = entry.getValue();
                }
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            }
            // 输出文件
            System.out.println("------------正在生成" + FILE_NAME + "文件---------------------------");
            genFile();
            System.out.println("------------成功生成" + FILE_NAME + "文件---------------------------");
        }

    }

    private static void genFile() {
        // 数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(DATESOURCE_URL);
        hikariConfig.setUsername(DATESOURCE_USER_NAME);
        hikariConfig.setPassword(DATESOURCE_PASS_WORD);
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource   dataSource   = new HikariDataSource(hikariConfig);
        // 生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir(SAVE_FILE_PATH)
                // 打开目录
                .openOutputDir(true)
                // 文件类型
                .fileType(FILE_TYPE)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker)
                // 自定义文件名称
                .fileName(FILE_NAME).build();

        // 忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("test_user");
        ignoreTableName.add("test_group");
        // 忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("test_");
        // 忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("_test");
        ProcessConfig processConfig = ProcessConfig.builder()
                // 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                // 根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                // 根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                // 根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                // 忽略表名
                .ignoreTableName(ignoreTableName)
                // 忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                // 忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
        // 配置
        Configuration config        = Configuration.builder()
                // 版本
                .version("1.0.0")
                // 描述
                .description(FILE_NAME + "设计文档生成")
                // 数据源
                .dataSource(dataSource)
                // 生成配置
                .engineConfig(engineConfig)
                // 生成配置
                .produceConfig(processConfig)
                .build();
        // 执行生成
        new DocumentationExecute(config).execute();
    }

}