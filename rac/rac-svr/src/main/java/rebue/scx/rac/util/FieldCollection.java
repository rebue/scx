package rebue.scx.rac.util;

/**
 * 信息导入字段
 * 
 * @author yuanman
 *
 */
public class FieldCollection {

    /**
     * 账户信息字段
     */
    public static String[] getAccountInformationCol() {
        String[] col = new String[10];
        col[0] = "index";
        col[1] = "signInName";
        col[2] = "signInNickname";
        col[3] = "idCard";
        col[4] = "signInMobile";
        col[5] = "signInEmail";
        col[6] = "orgId";
        col[7] = "orgName";
        col[8] = "roleId";
        col[9] = "roleName";
        return col;
    }

    /**
     * 组织信息字段
     */
    public static String[] getOrgInformationCol() {
        String[] col = new String[6];
        col[0] = "index";
        col[1] = "orgId";
        col[2] = "orgName";
        col[3] = "parentId";
        col[4] = "parentName";
        col[5] = "orgType";
        return col;
    }
}
