package jeoneunhye.vms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.domain.Video;

public class DataLoaderListener implements ApplicationContextListener {
  List<Video> videoList = new ArrayList<>();
  List<Member> memberList = new ArrayList<>();
  List<Board> boardList = new ArrayList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) {
    loadVideoData();
    loadMemberData();
    loadBoardData();

    context.put("videoList", videoList);
    context.put("memberList", memberList);
    context.put("boardList", boardList);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    saveVideoData();
    saveMemberData();
    saveBoardData();
  }

  @SuppressWarnings("unchecked")
  private void loadVideoData() {
    File file = new File("data/video.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      videoList = (List<Video>) in.readObject();

      System.out.printf("총 %d개의 영상 데이터를 로딩했습니다.\n", videoList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveVideoData() {
    File file = new File("data/video.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

      out.writeObject(videoList);

      System.out.printf("총 %d개의 영상 데이터를 저장했습니다.\n", videoList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("data/member.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      memberList = (List<Member>) in.readObject();

      System.out.printf("총 %d개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveMemberData() {
    File file = new File("data/member.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

      out.writeObject(memberList);

      System.out.printf("총 %d개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() {
    File file = new File("data/board.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      boardList = (List<Board>) in.readObject();

      System.out.printf("총 %d개의 게시글 데이터를 로딩했습니다.\n", boardList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveBoardData() {
    File file = new File("data/board.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

      out.writeObject(boardList);

      System.out.printf("총 %d개의 게시글 데이터를 저장했습니다.\n", boardList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }
}