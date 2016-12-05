package com.blackflight.dof.data.entity.mapper;

import com.blackflight.clean.data.entity.mapper.DataEntityMapper;
import com.blackflight.dof.data.entity.CameraEntity;
import com.blackflight.dof.domain.model.Camera;
import com.blackflight.dof.domain.repository.BodyRepository;
import com.blackflight.dof.domain.repository.LensRepository;

import java.util.UUID;

/**
 * Created by peter on 17-11-16.
 */

public class CameraEntityMapper implements DataEntityMapper<UUID, Camera, CameraEntity> {

    private final BodyRepository mBodyRepository;
    private final LensRepository mLensRepository;

    public CameraEntityMapper(BodyRepository bodyRepository, LensRepository lensRepository) {
        mBodyRepository = bodyRepository;
        mLensRepository = lensRepository;
    }

    @Override
    public Camera fromDataEntity(CameraEntity cameraEntity) throws Exception {
        Camera camera = new Camera(cameraEntity.getId(),
                mBodyRepository.getById(cameraEntity.getBodyId()),
                mLensRepository.getById(cameraEntity.getLensId()));

        return camera;
    }

    @Override
    public CameraEntity toDataEntity(Camera camera) {
        CameraEntity cameraEntity = new CameraEntity(camera.getId(),
                camera.getBody().getId(),
                camera.hasMountedLens() ? camera.getLens().getId() : null);

        return cameraEntity;
    }
}
