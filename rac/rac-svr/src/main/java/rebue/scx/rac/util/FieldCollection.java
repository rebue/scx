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
        String[] col = new String[9];
        col[0] = "signInName";
        col[1] = "signInNickname";
        col[2] = "idCard";
        col[3] = "signInMobile";
        col[4] = "signInEmail";
        col[5] = "orgId";
        col[6] = "orgName";
        col[7] = "roleId";
        col[8] = "roleName";
        return col;
    }

    /**
     * 组织信息字段
     */
    public static String[] getOrgInformationCol() {
        String[] col = new String[5];
        col[0] = "orgId";
        col[1] = "orgName";
        col[2] = "planTypeText";
        col[3] = "parentId";
        col[4] = "parentName";
        return col;
    }
}
