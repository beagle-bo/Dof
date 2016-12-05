package com.blackflight.dof.data.entity.mapper;

import com.blackflight.clean.data.entity.mapper.DataEntityMapper;
import com.blackflight.dof.data.entity.BodyTypeEntity;
import com.blackflight.dof.domain.model.BodyType;
import com.blackflight.dof.domain.repository.ImageFormatRepository;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public class BodyTypeEntityMapper implements DataEntityMapper<UUID, BodyType, BodyTypeEntity> {

    private final ImageFormatRepository mImageFormatRepository;

    public BodyTypeEntityMapper(ImageFormatRepository imageFormatRepository) {
        mImageFormatRepository = imageFormatRepository;
    }

    @Override
    public BodyType fromDataEntity(BodyTypeEntity bodyTypeEntity) throws Exception {
        BodyType bodyType = new BodyType(bodyTypeEntity.getId(),
                bodyTypeEntity.getModelName(),
                mImageFormatRepository.getById(bodyTypeEntity.getImageFormatId()));

        return bodyType;
    }

    @Override
    public BodyTypeEntity toDataEntity(BodyType bodyType) {
        BodyTypeEntity bodyTypeEntity = new BodyTypeEntity(bodyType.getId(),
                bodyType.getModelName(),
                bodyType.getImageFormat().getId());

        return bodyTypeEntity;
    }
}
