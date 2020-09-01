package bean;

/**
 * 录制视频列表Bean
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/9/9 16 : 48
 */
public class RecordingFileBean {
    //文件名称
    private String fileName;
    //文件路径
    private String filePath;
    //文件创建时间
    private String fileTime;
    //文件的封面路径
    private String fileCover;

    public String getFileCover() {
        return fileCover;
    }

    public void setFileCover(String fileCover) {
        this.fileCover = fileCover;
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
