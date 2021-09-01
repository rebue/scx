package com.github.rebue.scx.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.rebue.scx.mapper.OapAppMapper;
import com.github.rebue.scx.mo.OapAppMo;

@Repository
public class OapAppRepository {

    @Resource
    private OapAppMapper oapAppMapper;

    public OapAppMo selectByClientId(String clientId)
    {
        if (clientId == null) {
            return null;
        }
        OapAppMo mo = new OapAppMo();
        mo.setClientId(clientId);
        return oapAppMapper.selectOne(mo).orElse(null);
    }

}
