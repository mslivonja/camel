package org.apache.camel.example.util;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by eslimat on 22.1.2016..
 */
public class DefaultFileConsumer implements FileConsumer{

    private static Logger LOG = Logger.getLogger(DefaultFileConsumer.class);

    private File rootDir;

    private boolean singleFileAsDefault = true;

    public boolean isSingleFileAsDefault() {
        return singleFileAsDefault;
    }

    public void setSingleFileAsDefault(boolean useSingleFileAsDefault) {
        this.singleFileAsDefault = useSingleFileAsDefault;
    }

    /**
     * Get root directory.
     * Root directory is used when absolute path is not provided.
     *
     * This parameter is required.
     *
     * @return Root directory or <code>null</code> if not provided
     */
    public File getRootDir(Exchange exchange) {
        return rootDir;
    }

    public void setRootDir(File dir) {
        this.rootDir = dir;
    }

    /**
     * Consume file. Read file contents to string.
     *
     * @param f File to read
     * @return File content as String.class
     * @throws IOException If exception occurrs while reading file
     */
    protected String consumeFile(File f) throws IOException {

        char[] cbuf = new char[4096];
        StringBuilder sb = new StringBuilder();

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
     * Resolve file name from IN apache camel exchange.
     * If 'CameFileName' header is set file name is constructed using header attr. value. If file name
     * is not absolute path file name is prepanded with root directory.
     *
     * By default files are serched in root directory. If only one file exists there that file is returned
     * otherwise <code>null</code> is returned.
     *
     * @param exchange In exchange
     * @return File that should be consumed or <code>null</code> if it could NOT be resolved.
     */
    protected File resolveConsumeFile(Exchange exchange){
        if(exchange==null)
            // sanity check
            return null;

        // resolve file name if Exchange 'CamelFileName' header is set explicitly to some value
        String fileName = (String) exchange.getIn().getHeader(Exchange.FILE_NAME);

        if(!StringUtils.isEmpty(fileName)){
            // 'CamelFileName' header is set
            return getConsumeFile(fileName, exchange);
        }

        if(!isSingleFileAsDefault())
            // do not fallback to using single file in directory as
            // default file to consume
            return null;

        // fallback, search for files in root folder
        if(getRootDir(exchange)==null)
            return null;

        File[] fs = getRootDir(exchange).listFiles((FilenameFilter) FileFileFilter.FILE);

        if(fs.length==1)
            // if only one file exist in root folder
            return fs[0];

        // otherwise
        return null;
    }

    /**
     * Construct File using provided filename. If file name is not absolute path
     * prepend root directory.
     *
     * @param fileName File name
     * @return File constructed file object
     */
    protected File getConsumeFile(String fileName, Exchange exchange){
        if(StringUtils.isEmpty(fileName))
            return null;

        File rslt = new File(fileName);

        if(!rslt.isAbsolute())
            rslt = new File(getRootDir(exchange), fileName);

        return rslt;
    }

    @Handler
    public String consumeFile(Exchange exchange){

        File f = resolveConsumeFile(exchange);

        if(f==null) {
            LOG.info("Unable to consume file name in directory '" + rootDir.getAbsolutePath() + "'");
            return null;
        }

        LOG.trace("Consume file: '" + f.getAbsolutePath() + "' from directory '");
        try {
            return consumeFile(f);
        }catch(IOException e) {
            /// wrap IOException into runtime exception
            throw new RuntimeException("File consumer exception, " + e.getMessage(),e);
        }
    }
}