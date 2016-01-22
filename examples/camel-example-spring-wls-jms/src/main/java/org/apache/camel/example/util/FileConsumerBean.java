package org.apache.camel.example.util;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;

/**
 * Created by eslimat on 22.1.2016..
 */
public class FileConsumerBean {

    private static Logger LOG = Logger.getLogger(FileConsumerBean.class);

    private File rootDir;

    private int counter = 0;

    public File getRootDir() {
        return rootDir;
    }

    public void setRootDir(File dir) {
        this.rootDir = dir;
    }

    @Handler
    public String consumeFile(Exchange exchange)throws Exception{

        String fileName = resolveFileName(exchange);

        if(StringUtils.isEmpty(fileName)) {
            // reset counter
            int c = this.counter;
            this.counter = 0;
            throw new Exception("Unable to resolve file name for directory '" + rootDir.getAbsolutePath() + "' and ID[" + c + "]");
        }

        LOG.trace("Consume file: '" + fileName + "' from directory '" + rootDir.getAbsolutePath() + "'");

        char[] cbuf = new char[4096];
        StringBuilder sb = new StringBuilder();

        File f = new File(rootDir, fileName);
        FileReader fr = null;

        try{
            fr = new FileReader(f);

            int count = 0;
            while((count=fr.read(cbuf)) > 0)
                sb.append(cbuf, 0, count);
        }finally{
            if(fr!=null)
                fr.close();
        }

        return sb.toString();
    }

    /**
     * Resolve file Name of file that will be consumed
     *
     * @return file name of <code>null</code> if it could NOT be resolved
     */
    protected String resolveFileName(Exchange exchange){
        if(exchange==null)
            // sanity check
            return null;

        // resolve file name if Exchange CamelFileName header is set explicitly to some value
        String fileName = (String) exchange.getIn().getHeader(Exchange.FILE_NAME);

        if(!StringUtils.isEmpty(fileName))
            return fileName;

        File[] files = rootDir.listFiles( (FilenameFilter)FileFileFilter.FILE);

        // if only one file in given directory return just that file
        if(files!=null && files.length==1)
            return files[0].getName();

        int prefixId = (counter)% files.length;
        // prefix is something like 001 or 023
        String prefix = String.format("%03d", prefixId);

        // find file with matching prefix
        for(int i=0;i<files.length;i++){
            String fNm = files[i].getName();

            if(fNm.startsWith(prefix)) {
                // increase counter
                counter++;
                return fNm;
            }
        }

        // otherwise
        return null;
    }
}