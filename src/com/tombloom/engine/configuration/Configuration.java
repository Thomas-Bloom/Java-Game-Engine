package com.tombloom.javaengine.configuration;
import java.io.*;

public class Configuration {
    private int windowWidth, windowHeight, fpsCap;

    private final String configPath = "data/config.ini";
    private File configFile;

    public Configuration(){
        configFile = new File(configPath);
    }

    public void createFile(){
        try{
            // Create file if it doesn't already exist
            if(configFile.createNewFile()){
                System.out.println("File created");
                writeToFile();
            }
            else{
                System.out.println("File already exists");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeToFile(){
        try{
            PrintWriter writer = new PrintWriter(configPath);
            writer.println("WIDTH: " + 1280);
            writer.println("HEIGHT: " + 720);
            writer.println("FPS: " + 60);
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(configFile));

            String line;

            while((line = reader.readLine()) != null){
                if(line.startsWith("WIDTH")){
                    String[] lineSplit = line.split(": ");
                    windowWidth = Integer.parseInt(lineSplit[1]);
                }
                else if(line.startsWith("HEIGHT")){
                    String[] lineSplit = line.split(": ");
                    windowHeight = Integer.parseInt(lineSplit[1]);
                }
                else if(line.startsWith("FPS")){
                    String[] lineSplit = line.split(": ");
                    fpsCap = Integer.parseInt(lineSplit[1]);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getFpsCap() {
        return fpsCap;
    }
}
