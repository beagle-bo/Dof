package com.blackflight.dof.domain.repository;

import com.blackflight.clean.domain.repository.EntityRepository;
import com.blackflight.dof.domain.model.Lens;

import java.util.UUID;

/**
 * Created by peter on 17-11-16.
 */

public interface LensRepository extends EntityRepository<UUID, Lens> {
}
