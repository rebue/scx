package com.github.rebue.scx.mo.ex;

import com.github.rebue.scx.mo.OapAppMo;
import com.github.rebue.scx.mo.OapIpWhiteListMo;
import com.github.rebue.scx.mo.OapRedirectUriMo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class OapAppMoEx extends OapAppMo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 白名单IP
     */
    @Getter
    @Setter
    private OapIpWhiteListMo  ipWhiteList;
    /**
     * 重定向地址
     */
    @Getter
    @Setter
    private OapRedirectUriMo  redirectUri;

    public OapAppMoEx() {
        super();
    }

}
