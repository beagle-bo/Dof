package com.blackflight.dof.data.entity.mapper;

import com.blackflight.clean.data.entity.mapper.DataEntityMapper;
import com.blackflight.dof.data.entity.BodyEntity;
import com.blackflight.dof.domain.model.Body;
import com.blackflight.dof.domain.repository.BodyTypeRepository;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public class BodyEntityMapper implements DataEntityMapper<UUID, Body, BodyEntity> {

    private final BodyTypeRepository mBodyTypeRepository;

    public BodyEntityMapper(BodyTypeRepository bodyTypeRepository) {
        mBodyTypeRepository = bodyTypeRepository;
    }

    @Override
    public Body fromDataEntity(BodyEntity bodyEntity) throws Exception {
        Body body = new Body(bodyEntity.getId(),
                mBodyTypeRepository.getById(bodyEntity.getBodyTypeId()));

        return body;
    }

    @Override
    public BodyEntity toDataEntity(Body body) {
        BodyEntity bodyEntity = new BodyEntity(body.getId(),
                body.getBodyType().getId());

        return bodyEntity;
    }
}
