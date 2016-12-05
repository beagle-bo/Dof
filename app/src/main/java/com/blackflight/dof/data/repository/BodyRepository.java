package com.blackflight.dof.data.repository;

import com.blackflight.clean.data.repository.BaseRepository;
import com.blackflight.dof.data.entity.BodyEntity;
import com.blackflight.dof.data.entity.mapper.BodyEntityMapper;
import com.blackflight.dof.data.source.BodyDataSource;
import com.blackflight.dof.domain.model.Body;

import java.util.UUID;

/**
 * Created by peter on 11-11-16.
 */

public class BodyRepository extends BaseRepository<UUID, Body, BodyEntity> implements com.blackflight.dof.domain.repository.BodyRepository {

    private final com.blackflight.dof.domain.repository.BodyTypeRepository mBodyTypeRepository;

    private BodyRepository(BodyDataSource bodyDataSource,
                           com.blackflight.dof.domain.repository.BodyTypeRepository bodyTypeRepository) {
        super(bodyDataSource, new BodyEntityMapper(bodyTypeRepository));
        mBodyTypeRepository = bodyTypeRepository;
    }
}
