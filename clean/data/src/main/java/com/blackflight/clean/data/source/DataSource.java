package com.blackflight.clean.data.source;

import com.blackflight.clean.data.entity.DataEntity;
import com.blackflight.clean.domain.repository.Repository;

/**
 * Created by peter on 12-11-16.
 */

public interface DataSource<TKey, TDataEntity extends DataEntity<TKey>> extends Repository<TKey, TDataEntity> {
}
