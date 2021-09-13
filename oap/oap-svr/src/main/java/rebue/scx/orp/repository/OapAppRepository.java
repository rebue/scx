package rebue.scx.orp.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import rebue.scx.orp.mapper.OapAppMapper;
import rebue.scx.orp.mo.OapAppMo;

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
