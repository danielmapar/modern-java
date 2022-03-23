module com.udacity.catpoint.image {
    requires java.desktop;
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.services.rekognition;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.auth;
    requires slf4j.api;
    exports com.udacity.catpoint.service.image;
}