package com.blackflight.dof.domain.usecase;

import com.blackflight.clean.domain.usecase.UseCase;
import com.blackflight.dof.domain.model.Camera;
import com.blackflight.dof.domain.repository.CameraRepository;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by peter on 16-9-16.
 */
public class GetCamera implements UseCase<GetCamera.RequestValues, GetCamera.ResponseValue> {

    private CameraRepository mCameraRepository;

    @Inject
    public GetCamera(CameraRepository cameraRepository) {
        mCameraRepository = cameraRepository;
    }

    @Override
    public ResponseValue execute(RequestValues args) throws Exception {
        return null;
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final String mCameraId;

        public RequestValues(String cameraId) {
            mCameraId = checkNotNull(cameraId, "cameraId cannot be null!");
        }

        public String getCameraId() {
            return mCameraId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private Camera mCamera;

        public ResponseValue(Camera camera) {
            mCamera = checkNotNull(camera, "camera cannot be null!");
        }

        public Camera getCamera() {
            return mCamera;
        }
    }
}
