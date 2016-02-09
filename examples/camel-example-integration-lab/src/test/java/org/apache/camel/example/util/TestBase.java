package org.apache.camel.example.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by eslimat on 26.1.2016..
 */
public abstract class TestBase {

    private File tmpDir = new File("target/tmp");

    public File getTmpDir() {
        return tmpDir;
    }

    public void setTmpDir(File tmpDir) {
        this.tmpDir = tmpDir;
    }

    @Before
    public void setup() throws IOException {
        tearDown();
        // recreate target/tmp directory
        tmpDir.mkdir();
    }

    @After
    public void tearDown() throws IOException {
        File tmpDir = new File("target/tmp");

        if(tmpDir.exists()) {
            // delete target/tmp directory
            FileUtils.deleteDirectory(tmpDir);
        }
    }

    /**
     * Create new file and write contents to it.
     * If parent directories are missing they are created.
     * Relative paths are relative to tmp directory.
     *
     * @param filePath File path
     * @param content File content
     * @throws IOException I/O exception
     */
    protected void createNewFile(String filePath, String content) throws IOException {
        String dirNm = FilenameUtils.getFullPath(filePath);
        String   fNm = FilenameUtils.getName(filePath);
        File dir = new File(dirNm);


        if(!dir.isAbsolute())
            dir = new File(getTmpDir(), dirNm);

        if(!dir.exists())
            FileUtils.forceMkdir(dir);

        File file = new File(dir, fNm);
        createNewFile(file, content);
    }

    protected void createNewFile(File file, String content) throws IOException{
        if(file==null || content==null)
            return;

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(content);
        } finally {
            if (fw != null)
                fw.close();
        }
    }
}
