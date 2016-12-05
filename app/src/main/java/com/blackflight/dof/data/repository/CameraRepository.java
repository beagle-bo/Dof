package com.blackflight.dof.data.repository;

import com.blackflight.clean.data.repository.BaseRepository;
import com.blackflight.dof.data.entity.CameraEntity;
import com.blackflight.dof.data.entity.mapper.CameraEntityMapper;
import com.blackflight.dof.data.source.CameraDataSource;
import com.blackflight.dof.domain.model.Camera;

import java.util.UUID;

/**
 * Created by peter on 19-9-16.
 */
public class CameraRepository extends BaseRepository<UUID, Camera, CameraEntity> implements com.blackflight.dof.domain.repository.CameraRepository {

    private final com.blackflight.dof.domain.repository.BodyRepository mBodyRepository;
    private final com.blackflight.dof.domain.repository.LensRepository mLensRepository;

    private CameraRepository(CameraDataSource cameraDataSource,
                             com.blackflight.dof.domain.repository.BodyRepository bodyRepository,
                             com.blackflight.dof.domain.repository.LensRepository lensRepository) {
        super(cameraDataSource, new CameraEntityMapper(bodyRepository, lensRepository));
        mBodyRepository = bodyRepository;
        mLensRepository = lensRepository;
    }
}
