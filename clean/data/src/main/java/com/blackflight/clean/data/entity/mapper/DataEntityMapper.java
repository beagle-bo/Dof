package com.blackflight.clean.data.entity.mapper;

import com.blackflight.clean.data.entity.DataEntity;
import com.blackflight.clean.domain.model.Entity;

/**
 * Created by peter on 19-11-16.
 */

public interface DataEntityMapper<TKey, TEntity extends Entity<TKey>, TDataEntity extends DataEntity<TKey>> {

    public TEntity fromDataEntity(TDataEntity dataEntity) throws Exception;

    public TDataEntity toDataEntity(TEntity entity);
}
