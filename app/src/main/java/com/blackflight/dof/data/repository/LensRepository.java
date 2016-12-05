package com.blackflight.dof.data.repository;

import com.blackflight.clean.data.repository.BaseRepository;
import com.blackflight.dof.data.entity.LensEntity;
import com.blackflight.dof.data.entity.mapper.LensEntityMapper;
import com.blackflight.dof.data.source.LensDataSource;
import com.blackflight.dof.domain.model.Lens;

import java.util.UUID;

/**
 * Created by peter on 17-11-16.
 */

public class LensRepository extends BaseRepository<UUID, Lens, LensEntity> implements com.blackflight.dof.domain.repository.LensRepository {

    private final com.blackflight.dof.domain.repository.LensTypeRepository mLensTypeRepository;

    private LensRepository(LensDataSource lensDataSource,
                           com.blackflight.dof.domain.repository.LensTypeRepository lensTypeRepository) {
        super(lensDataSource, new LensEntityMapper(lensTypeRepository));
        mLensTypeRepository = lensTypeRepository;
    }
}
