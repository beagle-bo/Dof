package com.blackflight.dof.data.repository;

import com.blackflight.clean.data.repository.BaseRepository;
import com.blackflight.dof.data.entity.BodyTypeEntity;
import com.blackflight.dof.data.entity.mapper.BodyTypeEntityMapper;
import com.blackflight.dof.data.source.BodyTypeDataSource;
import com.blackflight.dof.domain.model.BodyType;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public class BodyTypeRepository extends BaseRepository<UUID, BodyType, BodyTypeEntity> implements com.blackflight.dof.domain.repository.BodyTypeRepository {

    private final com.blackflight.dof.domain.repository.ImageFormatRepository mImageFormatRepository;

    private BodyTypeRepository(BodyTypeDataSource bodyTypeDataSource,
                               com.blackflight.dof.domain.repository.ImageFormatRepository imageFormatRepository) {
        super(bodyTypeDataSource, new BodyTypeEntityMapper(imageFormatRepository));
        mImageFormatRepository = imageFormatRepository;
    }
}
