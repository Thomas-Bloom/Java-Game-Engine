package com.tombloom.javaengine.configuration;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfigurationFileSetup {
    private final String configPath = "data/config.ini";

    private File configFile;

    public ConfigurationFileSetup(){
        configFile = new File(configPath);
    }

    public void createFile(){
        try{
            // Create file if it doesn't already exist
            if(configFile.createNewFile()){
                System.out.println("File created");
            }
            else{
                System.out.println("File already exists");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeToFile(){
        try{
            PrintWriter writer = new PrintWriter(configPath);
            writer.println("WIDTH: " + 1280);
            writer.println("HEIGHT: " + 720);
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
