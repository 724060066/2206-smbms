package com.buba.service.provider;

import com.buba.pojo.Provider;

import java.util.List;

/**
 * 供应商service接口
 */
public interface ProviderService {

    /**
     * 查询供应商下拉列表
     * @return
     */
    List<Provider> listProviderForSelect();

}
