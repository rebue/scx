package com.github.rebue.scx.mo.ex;

import java.util.List;

import com.github.rebue.scx.mo.OapAppMo;

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
    private List<String>      ipAddrs;
    /**
     * 重定向地址
     */
    @Getter
    @Setter
    private List<String>      redirectUris;

    public OapAppMoEx() {
        super();
    }

}
