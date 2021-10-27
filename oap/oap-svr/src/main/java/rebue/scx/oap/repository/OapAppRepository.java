package rebue.scx.oap.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import rebue.scx.oap.mapper.OapAppMapper;
import rebue.scx.oap.mo.OapAppMo;

@Repository
public class OapAppRepository {

    @Resource
    private OapAppMapper oapAppMapper;

    public OapAppMo selectByClientId(String clientId) {
        if (clientId == null) {
            return null;
        }
        OapAppMo mo = new OapAppMo();
        mo.setClientId(clientId);
        return oapAppMapper.selectOne(mo).orElse(null);
    }

}
