package com.blackflight.dof.data.entity.mapper;

import com.blackflight.clean.data.entity.mapper.DataEntityMapper;
import com.blackflight.dof.data.entity.ImageFormatEntity;
import com.blackflight.dof.domain.model.ImageFormat;

import java.util.UUID;

/**
 * Created by peter on 18-11-16.
 */

public class ImageFormatEntityMapper implements DataEntityMapper<UUID, ImageFormat, ImageFormatEntity> {

    public ImageFormatEntityMapper() {
    }

    public ImageFormat fromDataEntity(ImageFormatEntity imageFormatEntity) throws Exception {
        ImageFormat imageFormat = new ImageFormat(imageFormatEntity.getId(),
                imageFormatEntity.getWidth(),
                imageFormatEntity.getHeight());

        return imageFormat;
    }

    public ImageFormatEntity toDataEntity(ImageFormat imageFormat) {
        ImageFormatEntity imageFormatEntity = new ImageFormatEntity(imageFormat.getId(),
                imageFormat.getWidth(),
                imageFormat.getHeight());

        return imageFormatEntity;
    }
}
