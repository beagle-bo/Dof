package com.blackflight.clean.data.repository;

import com.blackflight.clean.data.entity.DataEntity;
import com.blackflight.clean.data.entity.mapper.DataEntityMapper;
import com.blackflight.clean.data.source.DataSource;
import com.blackflight.clean.domain.model.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by peter on 19-11-16.
 */

public class BaseRepository<TKey, TEntity extends Entity<TKey>, TDataEntity extends DataEntity<TKey>> implements com.blackflight.clean.domain.repository.EntityRepository<TKey, TEntity> {

    private final DataSource<TKey, TDataEntity> mDataSource;
    private final DataEntityMapper<TKey, TEntity, TDataEntity> mDataEntityMapper;

    protected BaseRepository(DataSource<TKey, TDataEntity> dataSource, DataEntityMapper<TKey, TEntity, TDataEntity> dataEntityMapper) {
        mDataSource = dataSource;
        mDataEntityMapper = dataEntityMapper;
    }

    @Override
    public TEntity getById(TKey id) throws Exception {
        TDataEntity dataEntity = mDataSource.getById(id);
        TEntity entity = mDataEntityMapper.fromDataEntity(dataEntity);
        return entity;
    }

    @Override
    public Collection<TEntity> getAll() throws Exception {
        Collection<TDataEntity> dataEntities = mDataSource.getAll();
        List<TEntity> entities = new ArrayList<>();
        for (TDataEntity dataEntity : dataEntities) {
            entities.add(mDataEntityMapper.fromDataEntity(dataEntity));
        }
        return entities;
    }

    @Override
    public void addOrUpdate(TEntity entity) throws Exception {
        TDataEntity dataEntity = mDataEntityMapper.toDataEntity(entity);
        mDataSource.addOrUpdate(dataEntity);
    }

    @Override
    public void addOrUpdateAll(Collection<TEntity> entities) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteById(TKey id) throws Exception {
        mDataSource.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        mDataSource.deleteAll();
    }
}
