package rebue.scx.orp.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import rebue.scx.orp.dto.RedirectUris;
import rebue.scx.orp.mapper.OapRedirectUriMapper;
import rebue.scx.orp.mo.OapAppMo;
import rebue.scx.orp.mo.OapRedirectUriMo;

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
