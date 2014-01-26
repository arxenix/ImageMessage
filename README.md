ImageMessage
================================

Library for sending images in the minecraft chat!

Supports multiple image types, and animated .gifs too!


Installation
-------------------------
Simply copy the package com.bobacadodl.imgmessage into your plugin!

Usage
-------------------------
    public class ExamplePlugin extends JavaPlugin implements Listener{
        public void onEnable(){
            getServer().getPluginManager().registerEvents(this,this);
        }
    
        public void onJoin(PlayerJoinEvent event) throws IOException {
            BufferedImage imageToSend = ImageIO.read(this.getResource("creepermap.png"));
            new ImageMessage(
                    imageToSend,                     // the bufferedimage to send
                    8,                               // the image height
                    ImageChar.MEDIUM_SHADE.getChar() // the character that the image is made of
            ).appendText(
                    "",
                    "",
                    "",
                    "Thatssss a",
                    "niccceee house",
                    "you've got there"
            ).sendToPlayer(event.getPlayer());
        }
    }
