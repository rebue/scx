package rebue.scx.rac.mo.Ex;

import lombok.ToString;
import rebue.scx.rac.mo.RacOpLogMo;

/**
 * 操作日志翻页查询使用
 * 
 * @author yuanman
 *
 */

@ToString(callSuper = true)
public class RacOpLogExMo extends RacOpLogMo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 系统名称
	 */
	private String sysName;
	/**
	 * 系统备注
	 */
	private String remark;
	/**
	 * 菜单URN
	 */
	private String menuUrn;
	/**
	 * 领域ID
	 */
	private String domainId;

	/**
	 * 登录名称
	 */
	private String signInName;

	/**
	 * 登录账户昵称
	 */
	private String signInNickname;

	/**
	 * 登录手机
	 *
	 */
	private String signInMobile;

	/**
	 * 登录邮箱
	 *
	 */
	private String signInEmail;

	/**
	 * 微信昵称
	 */
	private String wxNickname;

	/**
	 * QQ昵称
	 */
	private String qqNickname;

	public RacOpLogExMo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RacOpLogExMo(String sysName, String remark, String menuUrn, String domainId, String signInName,
			String signInNickname, String signInMobile, String signInEmail, String wxNickname, String qqNickname) {
		super();
		this.sysName = sysName;
		this.remark = remark;
		this.menuUrn = menuUrn;
		this.domainId = domainId;
		this.signInName = signInName;
		this.signInNickname = signInNickname;
		this.signInMobile = signInMobile;
		this.signInEmail = signInEmail;
		this.wxNickname = wxNickname;
		this.qqNickname = qqNickname;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMenuUrn() {
		return menuUrn;
	}

	public void setMenuUrn(String menuUrn) {
		this.menuUrn = menuUrn;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getSignInName() {
		return signInName;
	}

	public void setSignInName(String signInName) {
		this.signInName = signInName;
	}

	public String getSignInNickname() {
		return signInNickname;
	}

	public void setSignInNickname(String signInNickname) {
		this.signInNickname = signInNickname;
	}

	public String getSignInMobile() {
		return signInMobile;
	}

	public void setSignInMobile(String signInMobile) {
		this.signInMobile = signInMobile;
	}

	public String getSignInEmail() {
		return signInEmail;
	}

	public void setSignInEmail(String signInEmail) {
		this.signInEmail = signInEmail;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getQqNickname() {
		return qqNickname;
	}

	public void setQqNickname(String qqNickname) {
		this.qqNickname = qqNickname;
	}

	

}
