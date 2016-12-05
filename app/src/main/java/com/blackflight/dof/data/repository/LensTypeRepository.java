package com.blackflight.dof.data.repository;

import com.blackflight.clean.data.repository.BaseRepository;
import com.blackflight.dof.data.entity.LensTypeEntity;
import com.blackflight.dof.data.entity.mapper.LensTypeEntityMapper;
import com.blackflight.dof.data.source.LensTypeDataSource;
import com.blackflight.dof.domain.model.LensType;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public class LensTypeRepository extends BaseRepository<UUID, LensType, LensTypeEntity> implements com.blackflight.dof.domain.repository.LensTypeRepository {

    private LensTypeRepository(LensTypeDataSource lensTypeDataSource) {
        super(lensTypeDataSource, new LensTypeEntityMapper());
    }
}
