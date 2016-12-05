package com.blackflight.dof.data.entity.mapper;

import com.blackflight.clean.data.entity.mapper.DataEntityMapper;
import com.blackflight.dof.data.entity.LensTypeEntity;
import com.blackflight.dof.domain.model.LensType;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public class LensTypeEntityMapper implements DataEntityMapper<UUID, LensType, LensTypeEntity> {

    public LensTypeEntityMapper() {
    }

    @Override
    public LensType fromDataEntity(LensTypeEntity lensTypeEntity) throws Exception {
        LensType lensType = new LensType(lensTypeEntity.getId(),
                lensTypeEntity.getModelName(),
                lensTypeEntity.getMinFocalDistance(),
                lensTypeEntity.getMaxFocalDistance(),
                LensType.FStopScale.valueOf(lensTypeEntity.getFStopScale()),
                lensTypeEntity.getMinApertureValue(),
                lensTypeEntity.getMaxApertureValue());

        return lensType;
    }

    @Override
    public LensTypeEntity toDataEntity(LensType lensType) {
        LensTypeEntity lensTypeEntity = new LensTypeEntity(lensType.getId(),
                lensType.getModelName(),
                lensType.getMinFocalDistance(),
                lensType.getMaxFocalDistance(),
                lensType.getFStopScale().toString(),
                lensType.getMinApertureValue(),
                lensType.getMaxApertureValue());

        return lensTypeEntity;
    }
}
