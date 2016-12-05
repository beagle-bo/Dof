package com.blackflight.dof.domain.repository;

import com.blackflight.clean.domain.repository.EntityRepository;
import com.blackflight.dof.domain.model.Camera;

import java.util.UUID;

/**
 * Created by peter on 19-9-16.
 */

public interface CameraRepository extends EntityRepository<UUID, Camera> {
}
