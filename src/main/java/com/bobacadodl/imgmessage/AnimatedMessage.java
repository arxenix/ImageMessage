package com.bobacadodl.imgmessage;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: bobacadodl
 * Date: 1/25/14
 * Time: 10:41 PM
 */
public class AnimatedMessage {
    private ImageMessage[] images;
    private int index = 0;

    public AnimatedMessage(ImageMessage... images) {
        this.images = images;
    }

    public AnimatedMessage(File gifFile, int height, char imgChar) throws IOException {
        List<BufferedImage> frames = getFrames(gifFile);
        images = new ImageMessage[frames.size()];
        for (int i = 0; i < frames.size(); i++) {
            images[i] = new ImageMessage(frames.get(i), height, imgChar);
        }
    }

    public List<BufferedImage> getFrames(File input) {
        List<BufferedImage> images = new ArrayList<BufferedImage>();
        try {
            ImageReader reader = ImageIO.getImageReadersBySuffix("GIF").next();
            ImageInputStream in = ImageIO.createImageInputStream(input);
            reader.setInput(in);
            for (int i = 0, count = reader.getNumImages(true); i < count; i++) {
                BufferedImage image = reader.read(i); // read next frame from gif
                images.add(image);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return images;
    }

    public ImageMessage current() {
        return images[index];
    }

    public ImageMessage next() {
        ++index;
        if (index >= images.length) {
            index = 0;
            return images[index];
        } else {
            return images[index];
        }
    }

    public ImageMessage previous() {
        --index;
        if (index <= 0) {
            index = images.length - 1;
            return images[index];
        } else {
            return images[index];
        }
    }

    public ImageMessage getIndex(int index) {
        return images[index];
    }
}
