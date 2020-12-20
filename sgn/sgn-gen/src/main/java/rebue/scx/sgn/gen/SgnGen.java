package rebue.scx.sgn.gen;

import java.io.IOException;
import java.sql.SQLException;

import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;

import rebue.mbgx.MybatisGeneratorWrap;

/**
 * 自动生成Mybatis
 */
public class SgnGen {

    public static void main(final String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        MybatisGeneratorWrap.gen(true, "conf/mbg-sgn.properties");
    }
}
