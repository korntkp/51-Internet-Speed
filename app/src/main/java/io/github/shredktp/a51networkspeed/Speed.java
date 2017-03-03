package io.github.shredktp.a51networkspeed;

/**
 * Created by Korshreddern on 02-Mar-17.
 */

public class Speed {
    private long downloadPerSec;
    private long uploadPerSec;

    public Speed(long downloadPerSec, long uploadPerSec) {
        this.downloadPerSec = downloadPerSec;
        this.uploadPerSec = uploadPerSec;
    }

    public long getDownloadPerSec() {
        return downloadPerSec;
    }

    public void setDownloadPerSec(long downloadPerSec) {
        this.downloadPerSec = downloadPerSec;
    }

    public long getUploadPerSec() {
        return uploadPerSec;
    }

    public void setUploadPerSec(long uploadPerSec) {
        this.uploadPerSec = uploadPerSec;
    }

    @Override
    public String toString() {
        return "Speed{" +
                "downloadPerSec=" + downloadPerSec +
                ", uploadPerSec=" + uploadPerSec +
                '}';
    }
}
