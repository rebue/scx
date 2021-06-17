package rebue.scx.rac.svc.impl.ex;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.CaseFormat;

import rebue.scx.rac.mapper.ex.RacUserAccountMapper;
import rebue.scx.rac.mo.ex.RacUserAccountMo;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.ex.RacUserAccountSvc;
import rebue.scx.rac.to.ex.RacUserAccountPageTo;

/**
 * 带用户信息的账户服务实现
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
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class RacUserAccountSvcImpl implements RacUserAccountSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     */
    @Lazy
    @Resource
    private RacAccountSvc        thisSvc;

    @Resource
    private RacUserAccountMapper mapper;

    /**
     * 分页查询带有用户的账户的信息
     *
     * @param qo 查询的具体条件
     */
    @Override
    public PageInfo<RacUserAccountMo> page(final RacUserAccountPageTo to) {
        final ISelect select = () -> mapper.page(to);
        if (StringUtils.isBlank(to.getOrderBy())) {
            return PageMethod.startPage(to.getPageNum(), to.getPageSize()).doSelectPageInfo(select);
        }
        else {
            // 将orderBy由小驼峰格式转化为数据库规范的大写下划线格式
            final String newOrderBy = Stream.of(to.getOrderBy().split(",")).map(item -> {
                final String[] split = item.trim().split(" ");
                final String   field = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, split[0]);
                return field + (split.length > 1 ? " " + split[1] : "");
            }).collect(Collectors.joining(","));
            return PageMethod.startPage(to.getPageNum(), to.getPageSize(), newOrderBy).doSelectPageInfo(select);
        }
    }
}
