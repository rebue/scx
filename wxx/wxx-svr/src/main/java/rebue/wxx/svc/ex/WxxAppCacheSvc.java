package rebue.wxx.svc.ex;

import java.util.List;

import rebue.wxx.cco.WxxAppCco;

public interface WxxAppCacheSvc {

    /**
     * 放入缓存
     */
    void putToCache(WxxAppCco cco);

    /**
     * 删除缓存
     */
    void delById(String id);

    /**
     * 获取缓存
     */
    WxxAppCco getById(String id);

    /**
     * 获取缓存列表
     */
    List<WxxAppCco> listAll();

}
