package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileReaderWriter {
    String fileSeperator;
    String textFilepath;
    final static Logger logger = LoggerFactory.getLogger(FileReaderWriter.class);

    public FileReaderWriter(){
        fileSeperator = System.getProperty("file.separator");
        textFilepath = System.getProperty("user.dir") +fileSeperator+ "ProductPriceFiles";
    }


    public void writeTextToFile(String text) {

        String filePath = createFilePath(System.getProperty("user.dir")+System.getProperty("file.separator")+"Price");

        try {
            OutputStream outputStream = new FileOutputStream(filePath + System.getProperty("file.separator") + "product.text");
            Writer outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(text);

            outputStreamWriter.close();
        } catch (Exception e) {
            logger.info("Write to file exception");
        }
    }

    public String createFilePath (String directory) {
        File fileDirectory = new File(directory);
        if (!fileDirectory.exists()) {
            if (fileDirectory.mkdir()) {
                System.out.println("Directory: " + directory + " is created!" );
                return directory;
            } else {
                logger.info("Failed to create directory: " + directory);
                return System.getProperty("user.dir");
            }
        } else {
            logger.info("Directory already exists: " + directory);
        }
        return directory;
    }
}
