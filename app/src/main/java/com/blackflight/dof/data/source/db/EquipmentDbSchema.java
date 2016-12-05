package com.blackflight.dof.data.source.db;

import android.provider.BaseColumns;

/**
 * Created by peter on 12-11-16.
 */

public class EquipmentDbSchema {

    public static final class CameraTable {
        public static final String NAME = "cameras";

        public static final class Columns implements BaseColumns {
            public static final String ID = "id";
            public static final String BODY_ID = "body_id";
            public static final String LENS_ID = "lens_id";
        }
    }

    public static final class BodyTable {
        public static final String NAME = "bodies";

        public static final class Columns implements BaseColumns {
            public static final String ID = "id";
            public static final String BODY_TYPE_ID = "body_type_id";
        }
    }

    public static final class BodyTypeTable {
        public static final String NAME = "body_types";

        public static final class Columns implements BaseColumns {
            public static final String ID = "id";
            public static final String MODEL_NAME = "model_name";
            public static final String IMAGE_FORMAT_ID = "image_format_id";
        }
    }

    public static final class ImageFormatTable {
        public static final String NAME = "image_formats";

        public static final class Columns implements BaseColumns {
            public static final String ID = "id";
            public static final String WIDTH = "width";
            public static final String HEIGHT = "height";
        }
    }

    public static final class LensTable {
        public static final String NAME = "lenses";

        public static final class Columns implements BaseColumns {
            public static final String ID = "id";
            public static final String LENS_TYPE_ID = "lens_type_id";
            public static final String FOCAL_DISTANCE = "focal_distance";
            public static final String APERTURE_VALUE = "aperture_value";
        }
    }

    public static final class LensTypeTable {
        public static final String NAME = "lens_types";

        public static final class Columns implements BaseColumns {
            public static final String ID = "id";
            public static final String MODEL_NAME = "model_name";
            public static final String F_STOP_SCALE = "f_stop_scale";
            public static final String MIN_FOCAL_DISTANCE = "min_focal_distance";
            public static final String MAX_FOCAL_DISTANCE = "max_focal_distance";
            public static final String MIN_APERTURE_VALUE = "min_aperture_value";
            public static final String MAX_APERTURE_VALUE = "max_aperture_value";
        }
    }
}
