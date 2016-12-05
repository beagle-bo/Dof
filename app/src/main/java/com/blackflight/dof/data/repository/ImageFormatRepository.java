package com.blackflight.dof.data.repository;

import com.blackflight.clean.data.repository.BaseRepository;
import com.blackflight.dof.data.entity.ImageFormatEntity;
import com.blackflight.dof.data.entity.mapper.ImageFormatEntityMapper;
import com.blackflight.dof.data.source.ImageFormatDataSource;
import com.blackflight.dof.domain.model.ImageFormat;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public class ImageFormatRepository extends BaseRepository<UUID, ImageFormat, ImageFormatEntity> implements com.blackflight.dof.domain.repository.ImageFormatRepository {

    private ImageFormatRepository(ImageFormatDataSource imageFormatDataSource) {
        super(imageFormatDataSource, new ImageFormatEntityMapper());
    }
}
