package rebue.scx.rac.svc.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacDicItemDao;
import rebue.scx.rac.jo.RacDicItemJo;
import rebue.scx.rac.mapper.RacDicItemMapper;
import rebue.scx.rac.mo.RacDicItemMo;
import rebue.scx.rac.svc.RacDicItemSvc;
import rebue.scx.rac.to.RacDicItemAddTo;
import rebue.scx.rac.to.RacDicItemDelTo;
import rebue.scx.rac.to.RacDicItemListTo;
import rebue.scx.rac.to.RacDicItemModifyTo;
import rebue.scx.rac.to.RacDicItemOneTo;
import rebue.scx.rac.to.RacDicItemPageTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 字典项服务实现
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
public class RacDicItemSvcImpl extends
        BaseSvcImpl<java.lang.Long, RacDicItemAddTo, RacDicItemModifyTo, RacDicItemDelTo, RacDicItemOneTo, RacDicItemListTo, RacDicItemPageTo, RacDicItemMo, RacDicItemJo, RacDicItemMapper, RacDicItemDao>
        implements RacDicItemSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacDicItemSvc thisSvc;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, RacDicItemAddTo, RacDicItemModifyTo, RacDicItemDelTo, RacDicItemOneTo, RacDicItemListTo, RacDicItemPageTo, RacDicItemMo, RacDicItemJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacDicItemMo> getMoClass() {
        return RacDicItemMo.class;
    }

    /**
     * 添加记录
     *
     * @param to 添加的参数
     *
     * @return 如果成功，且仅添加一条记录，返回添加时自动生成的ID，否则会抛出运行时异常
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacDicItemMo add(final RacDicItemAddTo to) {
        final RacDicItemMo mo = OrikaUtils.map(to, getMoClass());
        // mo中需要添加一个树编码
        if (to.getParentId() == null) {
            final RacDicItemMo qo = new RacDicItemMo();
            qo.setDicId(to.getDicId());
            Long count = _mapper.countDicSelective(qo);
            if (count != 0) {
                count = count - 1;
            }
            String treeCode = StringUtils.leftPad(count.toString(), 3, '0');
            while (true) {
                final RacDicItemOneTo oneTo = new RacDicItemOneTo();
                oneTo.setDicId(to.getDicId());
                oneTo.setTreeCode(treeCode);
                final RacDicItemMo one = thisSvc.getOne(oneTo);
                if (one == null) {
                    break;
                }
                else {
                    count    = count + 1;
                    treeCode = StringUtils.leftPad(count.toString(), 3, '0');
                }
            }
            mo.setTreeCode(treeCode);
        }
        else // 字典项下添加字典项
        {
            final RacDicItemMo itemMo = thisSvc.getById(to.getParentId());
            final RacDicItemMo qo     = new RacDicItemMo();
            qo.setDicId(to.getDicId());
            // .substring(0, itemMo.getTreeCode().length() == 3 ? itemMo.getTreeCode().length() : itemMo.getTreeCode().length() - 3));
            qo.setTreeCode(itemMo.getTreeCode());
            // 去除本身记录 -1
            Long   count    = _mapper.countDicItemSelective(qo);
            String treeCode = StringUtils.leftPad(count.toString(), 3, '0');
            while (true) {
                final RacDicItemOneTo oneTo = new RacDicItemOneTo();
                oneTo.setDicId(to.getDicId());
                oneTo.setTreeCode(itemMo.getTreeCode() + treeCode);
                final RacDicItemMo one = thisSvc.getOne(oneTo);
                if (one == null) {
                    break;
                }
                else {
                    count    = count + 1;
                    treeCode = StringUtils.leftPad(count.toString(), 3, '0');
                }
            }
            mo.setTreeCode(itemMo.getTreeCode() + treeCode);
        }
        mo.setUpdateDatetime(LocalDateTime.now());
        final RacDicItemMo addMo = thisSvc.addMo(mo);
        return addMo;
    }

    /**
     * 通过ID删除记录
     *
     * @param id 要删除记录的ID
     *
     * @return 如果成功，且删除一条记录，正常返回，否则会抛出运行时异常
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delById(Long id) {
        RacDicItemMo       itemMo    = _mapper.selectByPrimaryKey(id).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        String             treeCode  = itemMo.getTreeCode();
        // 查询删除该数据对其他数据的影响记录数据
        List<RacDicItemMo> selective = _mapper.selectDicSelective(itemMo);
        // 删除字典项
        final int          rowCount  = _mapper.deleteByPrimaryKey(id);
        // 删除字典项下的字典项
        int                count     = _mapper.deleteDicItemSelective(itemMo);
        // 对余下有影响的数据进行排序
        // FIXME 字项未进行相应的改动
        selective.stream().map(item -> {
            int length  = item.getTreeCode().length();
            int treeInt = Integer.parseInt(item.getTreeCode());
            treeInt = treeInt - 1;
            String tree = StringUtils.leftPad(treeInt + "", length, '0');
            item.setTreeCode(tree);
            _mapper.updateByPrimaryKey(item);
            return item;
        }).collect(Collectors.toList());
        if (rowCount == 0) {
            throw new RuntimeExceptionX("删除记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("删除记录异常，影响行数为" + rowCount);
        }
    }

}
