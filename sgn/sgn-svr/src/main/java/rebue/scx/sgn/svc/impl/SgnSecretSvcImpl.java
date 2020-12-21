package rebue.scx.sgn.svc.impl;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import rebue.scx.sgn.mapper.SgnSecretMapper;
import rebue.scx.sgn.mo.SgnSecretMo;
import rebue.scx.sgn.svc.SgnSecretSvc;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;

/**
 * 签名密钥服务实现
 *
 * <pre>
 * 注意：
 * 1. 查询数据库操作的方法，不用设置默认 @Transactional
 *    在类上方已经设置默认为 readOnly=true, propagation=Propagation.SUPPORTS
 *    而涉及到 增删改 数据库操作的方法时，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * 2. 事务不会针对受控异常（checked exception）回滚
 *    要想回滚事务，须抛出运行时异常(RuntimeException)
 * 3. 如果类上方不带任何参数的 @Transactional 注解时，如同下面的设置
 *    propagation(传播模式)=REQUIRED，readOnly=false，isolation(事务隔离级别)=READ_COMMITTED
 * </pre>
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class SgnSecretSvcImpl implements SgnSecretSvc {
    @Resource
    private SgnSecretMapper _mapper;
    @Resource
    protected Mapper        _dozerMapper;

    @Override
    public void add(@Valid final SgnSecretAddTo to) {
        final SgnSecretMo mo       = _dozerMapper.map(to, SgnSecretMo.class);
        final int         rowCount = _mapper.insertSelective(mo);
        if (rowCount != 1) {
            throw new RuntimeException("添加记录异常，影响行数为" + rowCount);
        }
    }

    @Override
    public void modifyById(@Valid final SgnSecretModifyTo to) {
        final SgnSecretMo mo       = _dozerMapper.map(to, SgnSecretMo.class);
        final int         rowCount = _mapper.updateByPrimaryKeySelective(mo);
        if (rowCount != 1) {
            throw new RuntimeException("修改记录异常，影响行数为" + rowCount);
        }
    }

    @Override
    public void delById(@NotNull final String id) {
        final int rowCount = _mapper.deleteByPrimaryKey(id);
        if (rowCount != 1) {
            throw new RuntimeException("删除记录异常，影响行数为" + rowCount);
        }
    }

    @Override
    public String getSecretById(@NotNull final String id) {
        return _mapper.selectByPrimaryKey(id).orElseThrow(() -> new NullPointerException("找不到密钥")).getSecret();
    }

}
