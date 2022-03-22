package com.udacity.catpoint.service.image;

import java.awt.image.BufferedImage;
public interface ImageService {
    boolean imageContainsCat(BufferedImage image, float confidenceThreshold);
}