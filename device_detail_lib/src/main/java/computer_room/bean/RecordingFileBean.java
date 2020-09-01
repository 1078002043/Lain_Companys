package computer_room.bean;

/**
 * 录制视频列表Bean
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/9/9 16 : 48
 */
public class RecordingFileBean {

    private String fileName;
    private String filePath;

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
