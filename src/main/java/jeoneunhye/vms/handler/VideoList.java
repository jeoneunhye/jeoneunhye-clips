package jeoneunhye.vms.handler;

import java.util.Arrays;
import jeoneunhye.vms.domain.Video;

public class VideoList {
  private static final int VIDEO_SIZE = 100;
  private Video[] videos;
  private int videoCount = 0;
  
  public VideoList() {
    this.videos = new Video[VIDEO_SIZE];
  }
  
  public VideoList(int capacity) {
    if (capacity > VIDEO_SIZE &&
        capacity < 10000) {
    this.videos = new Video[capacity];
    } else {
      this.videos = new Video[VIDEO_SIZE];
    }
  }
  
  public void add(Video video) {
    if (this.videoCount == this.videos.length) {
      int oldCapacity = this.videos.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.videos = Arrays.copyOf(this.videos, newCapacity);
    }
    this.videos[this.videoCount++] = video;
  }
  
  public Video[] toArray() {
    return Arrays.copyOf(videos, videoCount);
  }
}