package com.buba.service.provider;

import com.buba.dao.provider.ProviderDao;
import com.buba.pojo.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @date 2023/3/24 09:00
 */
@Service("providerService")
public class ProviderServiceImpl implements ProviderService{
    @Autowired
    private ProviderDao providerDao;

    /**
     * 查询供应商下拉列表
     * @return
     */
    @Override
    public List<Provider> listProviderForSelect() {
        // 将查询结果返回给servlet
        return providerDao.listProviderForSelect();
    }
}
