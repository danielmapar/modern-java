module com.udacity.catpoint.image {
    requires java.desktop;
    requires slf4j.api;
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.services.rekognition;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.auth;
    exports com.udacity.catpoint.service.image;
}