package com.blackflight.dof.data.entity.mapper;

import com.blackflight.clean.data.entity.mapper.DataEntityMapper;
import com.blackflight.dof.data.entity.LensEntity;
import com.blackflight.dof.domain.model.Lens;
import com.blackflight.dof.domain.repository.LensTypeRepository;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public class LensEntityMapper implements DataEntityMapper<UUID, Lens, LensEntity> {
    
    private final LensTypeRepository mLensTypeRepository;

    public LensEntityMapper(LensTypeRepository lensTypeRepository) {
        mLensTypeRepository = lensTypeRepository;
    }

    @Override
    public Lens fromDataEntity(LensEntity lensEntity) throws Exception {
        Lens lens = new Lens(lensEntity.getId(),
                mLensTypeRepository.getById(lensEntity.getLensTypeId()),
                lensEntity.getFocalDistance(),
                lensEntity.getApertureValue(),
                lensEntity.getSubjectDistance());

        return lens;
    }

    @Override
    public LensEntity toDataEntity(Lens lens) {
        LensEntity lensEntity = new LensEntity(lens.getId(),
                lens.getLensType().getId(),
                lens.getFocalDistance(),
                lens.getApertureValue(),
                lens.getSubjectDistance());

        return lensEntity;
    }
}
