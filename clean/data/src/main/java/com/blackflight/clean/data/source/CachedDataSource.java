package com.blackflight.clean.data.source;

import com.blackflight.clean.data.entity.DataEntity;

import java.util.Collection;

/**
 * Created by peter on 28-11-16.
 */

public class CachedDataSource<TKey, TDataEntity extends DataEntity<TKey>> implements DataSource<TKey, TDataEntity> {
    private DataSource<TKey, TDataEntity> mWrappedDataSource;
    private DataSource<TKey, TDataEntity> mCacheDataSource;
    private boolean cachedAll = false;

    public CachedDataSource(DataSource<TKey,TDataEntity> wrappedDataSource, DataSource<TKey,TDataEntity> cacheDataSource) {
        mWrappedDataSource = wrappedDataSource;
        mCacheDataSource = cacheDataSource;
    }

    public void invalidateCache() throws Exception {
        mCacheDataSource.deleteAll();
        cachedAll = false;
    }

    @Override
    public TDataEntity getById(TKey id) throws Exception {
        TDataEntity dataEntity = mCacheDataSource.getById(id);

        if (dataEntity == null) {
            dataEntity = mWrappedDataSource.getById(id);
            mCacheDataSource.addOrUpdate(dataEntity);
        }
        return dataEntity;
    }

    @Override
    public Collection<TDataEntity> getAll() throws Exception {
        Collection<TDataEntity> dataEntities = null;

        if (cachedAll) {
            dataEntities = mCacheDataSource.getAll();
        } else {
            dataEntities = mWrappedDataSource.getAll();
            mCacheDataSource.addOrUpdateAll(dataEntities);
            cachedAll = true;
        }
        return dataEntities;
    }

    @Override
    public void addOrUpdate(TDataEntity dataEntity) throws Exception {
        mCacheDataSource.addOrUpdate(dataEntity);
        mWrappedDataSource.addOrUpdate(dataEntity);
    }

    @Override
    public void addOrUpdateAll(Collection<TDataEntity> dataEntities) throws Exception {
        mCacheDataSource.addOrUpdateAll(dataEntities);
        mWrappedDataSource.addOrUpdateAll(dataEntities);
        cachedAll = true;
    }

    @Override
    public void deleteById(TKey id) throws Exception {
        mCacheDataSource.deleteById(id);
        mWrappedDataSource.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        mCacheDataSource.deleteAll();
        mWrappedDataSource.deleteAll();
    }
}
