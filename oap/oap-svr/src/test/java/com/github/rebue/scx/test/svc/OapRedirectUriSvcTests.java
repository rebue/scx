package com.github.rebue.scx.test.svc;

import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.PageInfo;
import com.github.rebue.scx.mo.OapRedirectUriMo;
import com.github.rebue.scx.to.OapRedirectUriAddTo;
import com.github.rebue.scx.to.OapRedirectUriModifyTo;
import com.github.rebue.scx.to.OapRedirectUriPageTo;
import com.github.rebue.scx.svc.OapRedirectUriSvc;

import lombok.extern.slf4j.Slf4j;
import rebue.wheel.core.RandomEx;

/**
 *  Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class OapRedirectUriSvcTests {

    /**
     * 要测试的微服务
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private OapRedirectUriSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper            dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        OapRedirectUriAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (OapRedirectUriAddTo) RandomEx.randomPojo(OapRedirectUriAddTo.class);
            log.info("添加的参数为：" + addTo);
            final OapRedirectUriMo addRo = _svc.add(addTo);
            log.info("添加的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<OapRedirectUriMo> pageResult = _svc.page(new OapRedirectUriPageTo());
        log.info("查询的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个的参数为：" + id);
        OapRedirectUriMo getByIdResult = _svc.getById(id);
        log.info("获取单个的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);

        final OapRedirectUriModifyTo modifyTo = dozerMapper.map(addTo, OapRedirectUriModifyTo.class);
        modifyTo.setId(id);
        log.info("修改的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);

        log.info("删除的参数为：" + id);
        _svc.delById(id);
    }
}
