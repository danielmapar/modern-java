module com.udacity.catpoint.security {
    requires com.google.gson;
    requires com.google.common;
    requires miglayout;
    requires java.desktop;
    requires java.prefs;
    requires com.udacity.catpoint.image;
    opens com.udacity.catpoint.data to com.google.gson;
}