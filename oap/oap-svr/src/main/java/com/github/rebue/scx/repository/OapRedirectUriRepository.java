package com.github.rebue.scx.repository;

import com.github.rebue.scx.dto.RedirectUris;
import com.github.rebue.scx.mapper.OapRedirectUriMapper;
import com.github.rebue.scx.mo.OapAppMo;
import com.github.rebue.scx.mo.OapRedirectUriMo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class OapRedirectUriRepository {

    @Resource
    private OapAppRepository oapAppRepository;

    @Resource
    private OapRedirectUriMapper oapRedirectUriMapper;

    public RedirectUris getRedirectUris(String clientId)
    {
        OapAppMo app = oapAppRepository.selectByClientId(clientId);
        if (app == null) {
            return RedirectUris.empty();
        }
        OapRedirectUriMo mo = new OapRedirectUriMo();
        mo.setAppId(app.getId());
        List<OapRedirectUriMo> list = oapRedirectUriMapper.selectSelective(mo);
        return new RedirectUris(list);

    }

}
