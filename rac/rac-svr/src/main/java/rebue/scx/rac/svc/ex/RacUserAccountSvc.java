package rebue.scx.rac.svc.ex;

import org.springframework.validation.annotation.Validated;

import com.github.pagehelper.PageInfo;

import rebue.scx.rac.mo.ex.RacUserAccountMo;
import rebue.scx.rac.to.ex.RacUserAccountPageTo;

/**
 * 带用户信息的账户服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 */
@Validated
public interface RacUserAccountSvc {

	/**
	 * 查询带有用户的账户的信息
	 *
	 * @param qo 查询的具体条件
	 */
	PageInfo<RacUserAccountMo> page(RacUserAccountPageTo qo);

	/**
	 * 根据ID查询有用户信息的账户
	 *
	 * @param id
	 */
	RacUserAccountMo getByAccountId(Long id);
}
