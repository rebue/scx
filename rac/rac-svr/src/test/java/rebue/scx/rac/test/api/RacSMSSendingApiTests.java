package rebue.scx.rac.test.api;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacSMSSendingApi;
import rebue.scx.rac.to.ex.RacSMSTo;

/**
 * 账户 API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacSMSSendingApiTests {

    /**
     * 要测试的API
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private RacSMSSendingApi _api;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper           dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacSMSTo addTo = new RacSMSTo();
        Long     id    = null;
        addTo.setAccountId(10L);
        addTo.setCaptchaVerification("sdwd");
        final Ro<?> pageResult = _api.sendTemplateSMS(addTo);
        log.info("查询账户的返回值为：" + pageResult);

        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
    }
}
