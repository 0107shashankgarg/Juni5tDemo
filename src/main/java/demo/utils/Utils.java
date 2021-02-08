package demo.utils;


import demo.config.ConfigMapping;
import org.aeonbits.owner.ConfigCache;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class Utils {

    private static final Logger LOG = LogManager.getLogger(Utils.class);

    private static ConfigMapping cfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());

    /**
     * Code snippet from https://stackoverflow.com/a/14427280/4894316
     *
     * @param dir directory with tar.gz files inside
     * @throws IOException
     */
    public static void extractTarGzFiles(File dir) throws IOException {
        File[] listDir = dir.listFiles();
        if (listDir == null || listDir.length == 0) {
            return;
        }

        for (File i : listDir) {
                /*  Warning! this will try and extract all files in the directory
                if other files exist, a for loop needs to go here to check that
                the file (i) is an archive file before proceeding */
            if ((i.isDirectory()) || (!i.getName().endsWith(".tar.gz"))) {
                continue;
            }

            LOG.info("Extracting file: " + i.getName());

            String fileName = i.toString();
            String tarFileName = fileName + ".tar";
            FileInputStream inStream = new FileInputStream(fileName);
            GZIPInputStream gInStream = new GZIPInputStream(inStream);
            FileOutputStream outStream = new FileOutputStream(tarFileName);
            byte[] buf = new byte[1024];
            int len;
            while ((len = gInStream.read(buf)) > 0) {
                outStream.write(buf, 0, len);
            }
            gInStream.close();
            outStream.close();

            // There should now be tar files in the directory
            // extract specific files from tar
            TarArchiveInputStream myTarFile = new TarArchiveInputStream(new FileInputStream(tarFileName));
            TarArchiveEntry entry = null;
            int offset;
            FileOutputStream outputFile = null;

            // read every single entry in TAR file
            while ((entry = myTarFile.getNextTarEntry()) != null) {
                // the following two lines remove the .tar.gz extension for the folder name
                String folderName = i.getName().substring(0, i.getName().lastIndexOf('.'));
                folderName = folderName.substring(0, folderName.lastIndexOf('.'));

                File outputDir = new File(i.getParent() + "/" + folderName + "/" + entry.getName());
                if (!outputDir.getParentFile().exists()) {
                    outputDir.getParentFile().mkdirs();
                }
                // if the entry in the tar is a directory, it needs to be created, only files can be extracted
                if (entry.isDirectory()) {
                    outputDir.mkdirs();
                } else {
                    byte[] content = new byte[(int) entry.getSize()];
                    offset = 0;
                    myTarFile.read(content, offset, content.length - offset);
                    outputFile = new FileOutputStream(outputDir);
                    IOUtils.write(content, outputFile);
                    outputFile.close();
                }
            }
            //close and delete the tar files, leaving the original .tar.gz and the extracted folders
            myTarFile.close();
            File tarFile = new File(tarFileName);
            tarFile.delete();
        }
    }

    private static List<File> getFilesByWildcards(String directoryName, String extension) {
        File directory = new File(directoryName);
        return (List<File>)
                FileUtils.listFiles(directory, new WildcardFileFilter(Collections.singletonList(extension)), TrueFileFilter.INSTANCE);
    }

    public static List<File> getFilesByPatterns(String directoryName, String[] filePatterns) {
        // Add files by priority in formats, described in filePatterns array
        // Ex. Voice sources should ingest audio files firstly
        return Arrays.stream(filePatterns)
                .flatMap(fp -> getFilesByWildcards(directoryName, fp).stream())
                .collect(Collectors.toList());
    }







    public static String fileFromResources(String filename) {
        try {
            return Thread.currentThread().getContextClassLoader().getResource(filename).getFile();
        } catch (NullPointerException e) {
            throw new RuntimeException("Can't find file: " + filename);
        }
    }
}
