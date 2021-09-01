package com.github.rebue.scx.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.github.rebue.scx.mo.OapGrantMo;
import com.github.rebue.scx.svc.OapGrantSvc;
import com.github.rebue.scx.to.OapGrantAddTo;
import com.github.rebue.scx.to.OapGrantModifyTo;
import com.github.rebue.scx.to.OapGrantPageTo;

import lombok.extern.slf4j.Slf4j;
import rebue.wheel.core.RandomEx;

/**
 * 三方应用账户信息 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class OapGrantSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private OapGrantSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper      dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        OapGrantAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (OapGrantAddTo) RandomEx.randomPojo(OapGrantAddTo.class);
            log.info("添加三方应用账户信息的参数为：" + addTo);
            final OapGrantMo addRo = _svc.add(addTo);
            log.info("添加三方应用账户信息的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<OapGrantMo> pageResult = _svc.page(new OapGrantPageTo());
        log.info("查询三方应用账户信息的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个三方应用账户信息的参数为：" + id);
        OapGrantMo getByIdResult = _svc.getById(id);
        log.info("获取单个三方应用账户信息的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final OapGrantModifyTo modifyTo = dozerMapper.map(addTo, OapGrantModifyTo.class);
        modifyTo.setId(id);
        log.info("修改三方应用账户信息的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除三方应用账户信息的参数为：" + id);
        _svc.delById(id);
    }
}
