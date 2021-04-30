package rebue.scx.rac.mo.Ex;

import lombok.ToString;
import rebue.scx.rac.mo.RacLockLogMo;
import rebue.scx.rac.mo.RacOpLogMo;

/**
 * 操作日志翻页查询使用
 * 
 * @author yuanman
 *
 */
@ToString(callSuper = true)
public class RacLockLogExMo extends RacLockLogMo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 登录名称
	 */
	private String signInName; 
	/**
	 * 登录账户昵称
	 */
	private String signInNickname;
	/**
	 * 微信昵称
	 */
	private String wxNickname; 
	/**
	 * QQ昵称
	 */
	private String qqNickname;
	
	/**
	 * 锁定登录名称
	 */
	private String locksignInName; 
	/**
	 * 锁定登录账户昵称
	 */
	private String locksignInNickname;
	/**
	 * 锁定微信昵称
	 */
	private String lockwxNickname; 
	/**
	 * 锁定QQ昵称
	 */
	private String lockqqNickname;
	
	/**
	 * 解锁登录名称
	 */
	private String unlocksignInName; 
	/**
	 * 解锁登录账户昵称
	 */
	private String unlocksignInNickname;
	/**
	 * 解锁微信昵称
	 */
	private String unlockwxNickname; 
	/**
	 * 解锁QQ昵称
	 */
	private String unlockqqNickname;

	public RacLockLogExMo() {
		super();
	}

	public RacLockLogExMo(String signInName, String signInNickname, String wxNickname, String qqNickname,
			String locksignInName, String locksignInNickname, String lockwxNickname, String lockqqNickname,
			String unlocksignInName, String unlocksignInNickname, String unlockwxNickname, String unlockqqNickname) {
		super();
		this.signInName = signInName;
		this.signInNickname = signInNickname;
		this.wxNickname = wxNickname;
		this.qqNickname = qqNickname;
		this.locksignInName = locksignInName;
		this.locksignInNickname = locksignInNickname;
		this.lockwxNickname = lockwxNickname;
		this.lockqqNickname = lockqqNickname;
		this.unlocksignInName = unlocksignInName;
		this.unlocksignInNickname = unlocksignInNickname;
		this.unlockwxNickname = unlockwxNickname;
		this.unlockqqNickname = unlockqqNickname;
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

	public String getLocksignInName() {
		return locksignInName;
	}

	public void setLocksignInName(String locksignInName) {
		this.locksignInName = locksignInName;
	}

	public String getLocksignInNickname() {
		return locksignInNickname;
	}

	public void setLocksignInNickname(String locksignInNickname) {
		this.locksignInNickname = locksignInNickname;
	}

	public String getLockwxNickname() {
		return lockwxNickname;
	}

	public void setLockwxNickname(String lockwxNickname) {
		this.lockwxNickname = lockwxNickname;
	}

	public String getLockqqNickname() {
		return lockqqNickname;
	}

	public void setLockqqNickname(String lockqqNickname) {
		this.lockqqNickname = lockqqNickname;
	}

	public String getUnlocksignInName() {
		return unlocksignInName;
	}

	public void setUnlocksignInName(String unlocksignInName) {
		this.unlocksignInName = unlocksignInName;
	}

	public String getUnlocksignInNickname() {
		return unlocksignInNickname;
	}

	public void setUnlocksignInNickname(String unlocksignInNickname) {
		this.unlocksignInNickname = unlocksignInNickname;
	}

	public String getUnlockwxNickname() {
		return unlockwxNickname;
	}

	public void setUnlockwxNickname(String unlockwxNickname) {
		this.unlockwxNickname = unlockwxNickname;
	}

	public String getUnlockqqNickname() {
		return unlockqqNickname;
	}

	public void setUnlockqqNickname(String unlockqqNickname) {
		this.unlockqqNickname = unlockqqNickname;
	}

	
	
}
