package com.bobacadodl.imgmessage;

import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;

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
        this(ImageIO.createImageInputStream(gifFile), height, imgChar);
    }

    public AnimatedMessage(ImageInputStream imageInputStream, int height, char imgChar) throws IOException {
        List<BufferedImage> frames = getFrames(imageInputStream);
        images = new ImageMessage[frames.size()];
        for (int i = 0; i < frames.size(); i++) {
            images[i] = new ImageMessage(frames.get(i), height, imgChar);
        }
    }

    public ArrayList<BufferedImage> getFrames(ImageInputStream imageInputStream) throws IOException {
        ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
        ImageReader ir = new GIFImageReader(new GIFImageReaderSpi());
        ir.setInput(ImageIO.createImageInputStream(imageInputStream));
        for (int i = 0; i < ir.getNumImages(true); i++)
            frames.add(ir.read(i));
        return frames;
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
