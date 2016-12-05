package com.blackflight.dof.domain.repository;

import com.blackflight.clean.domain.repository.EntityRepository;
import com.blackflight.dof.domain.model.LensType;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public interface LensTypeRepository extends EntityRepository<UUID, LensType> {
}
