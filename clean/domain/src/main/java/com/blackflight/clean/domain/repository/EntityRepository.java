package com.blackflight.clean.domain.repository;

import com.blackflight.clean.domain.model.Entity;

/**
 * Created by peter on 28-11-16.
 */

public interface EntityRepository<TKey, TEntity extends Entity<TKey>> extends Repository<TKey, TEntity> {
}
