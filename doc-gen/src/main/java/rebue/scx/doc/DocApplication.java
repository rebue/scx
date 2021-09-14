package rebue.scx.doc;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Map;

import static rebue.scx.doc.Utils.concat;

/**
 * 生成数据库表结构文档
 *
 * @author yuanman
 */
public class DocApplication {

    private static final String filePathPrefix = "C:\\Users\\lq\\Desktop\\maiyue-workspace\\rebue\\server\\scx";

    /**
     * 生成文件的保存路径
     */
    private static final String SAVE_FILE_PATH = "C:\\Users\\lq\\Desktop";
    /**
     * 生成的文件类型
     */
    private static final EngineFileType FILE_TYPE = EngineFileType.WORD;
    /**
     * 生成的文档版本
     */
    private static final String VERSION = "1.0.0";
    /**
     * 读取数据源的Key,不可修改
     */
    private static final String[] fields = new String[]{
            "spring.datasource.url",
            "spring.datasource.username",
            "spring.datasource.password"
    };

    public static void main(final String[] args)
    {
        String filePathSuffix = concat("src", "main", "resources", "config");
        genFile("rac", concat(filePathPrefix, "rac", "rac-svr", filePathSuffix), "hwy");
        genFile("oap", concat(filePathPrefix, "oap", "oap-svr", filePathSuffix), "hwy");
        genFile("oss", concat(filePathPrefix, "oss", "oss-svr", filePathSuffix), "hwy");
        genFile("rrl", concat(filePathPrefix, "rrl", "rrl-svr", filePathSuffix), "hwy");
    }

    private static String transformUrl(String raw)
    {
        return raw.replace("mysql:3306", "122.9.104.17:3306");
    }

    private static String transformUsername(String raw)
    {
        // return "root";
        return raw;
    }

    private static String transformPassword(String raw)
    {
        // return "xxxxxxxx";
        return raw;
    }

    /**
     * @param databaseName 数据库名称，生成的文件名
     * @param path         读取数据源的配置文件路径，即yml文件 (path/bootstrap-xxx.yml)
     * @param type         筛选数据源配置文件dev或者prod
     */
    private static void genFile(String databaseName, String path, String type)
    {
        Map<String, String> map = YmlProperties.getProperties(path, type, fields);
        // 数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(transformUrl(map.get(fields[0])));
        hikariConfig.setUsername(transformUsername(map.get(fields[1])));
        hikariConfig.setPassword(transformPassword(map.get(fields[2])));
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);
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
                .fileName(databaseName + "数据库").build();

        // 忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();
        // ignoreTableName.add("test_user");
        // ignoreTableName.add("test_group");
        // 忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();
        // ignorePrefix.add("test_");
        // 忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        // ignoreSuffix.add("_test");
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
        Configuration config = Configuration.builder()
                // 版本
                .version(VERSION)
                // 描述
                .description(databaseName + "数据库设计文档生成")
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