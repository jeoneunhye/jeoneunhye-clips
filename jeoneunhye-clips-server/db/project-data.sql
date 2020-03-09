-- 영상 예제 데이터 
insert into vms_video(video_id, subject, titl, url, playtime, uploader, upload_dt)
  values(101, '음악', 'autumn breeze', 'https://www.youtube.com/watch?v=EzQsoZYY470', '3:44', 'JIDA', '2016-10-20');
insert into vms_video(video_id, subject, titl, url, playtime, uploader, upload_dt)
  values(102, '재테크', '미국 주식 한달에 1주', 'https://www.youtube.com/watch?v=MgBz1ShkYjk', '30:58', '재테크 읽어주는 파일럿', '2020-3-6');
insert into vms_video(video_id, subject, titl, url, playtime, uploader, upload_dt)
  values(103, '스포츠', '칼소폭 유산소 운동', 'https://www.youtube.com/watch?v=VNQpP6C1fJg', '33:11', '땅끄부부', '2017-11-27');
insert into vms_video(video_id, subject, titl, url, playtime, uploader, upload_dt)
  values(104, '레시피', '간장국수', 'https://www.youtube.com/watch?v=bof84ayScPQ', '7:03', '박막례 할머니', '2019-3-3');
insert into vms_video(video_id, subject, titl, url, playtime, uploader, upload_dt)
  values(105, 'IT', '배민 리드 개발자', 'https://www.youtube.com/watch?v=V9AGvwPmnZU', '10:54', 'EO', '2019-12-3');

-- 회원 예제 데이터
insert into vms_member(member_id, id, nickname, pwd, phone, email, cdt) 
  values(11, 'user1', 'nickname1', password('1111'), '010-0000-0000', 'user1@test.com', '2020-1-1');
insert into vms_member(member_id, id, nickname, pwd, phone, email, cdt) 
  values(12, 'user2', 'nickname2', password('1111'), '010-0000-0000', 'user2@test.com', '2020-2-2');
insert into vms_member(member_id, id, nickname, pwd, phone, email, cdt) 
  values(13, 'user3', 'nickname3', password('1111'), '010-0000-0000', 'user3@test.com', '2020-3-3');

-- 게시글 예제 데이터
insert into vms_board(board_id, titl, conts, writer) 
  values(1, '제목1', '내용1', '글쓴이1');
insert into vms_board(board_id, titl, conts, writer) 
  values(2, '제목2', '내용2', '글쓴이2');
insert into vms_board(board_id, titl, conts, writer) 
  values(3, '제목3', '내용3', '글쓴이3');
insert into vms_board(board_id, titl, conts, writer) 
  values(4, '제목4', '내용4', '글쓴이4');
insert into vms_board(board_id, titl, conts, writer) 
  values(5, '제목5', '내용5', '글쓴이5');

-- 영상 사진 게시글 예제 데이터
insert into vms_photo(photo_id, video_id, titl, conts)
  values(1, 101, 'test1-101', '내용1');
insert into vms_photo(photo_id, video_id, titl, conts)
  values(2, 102, 'test2-102', '내용2');
insert into vms_photo(photo_id, video_id, titl, conts)
  values(3, 103, 'test3-103', '내용3');
insert into vms_photo(photo_id, video_id, titl, conts)
  values(4, 104, 'test4-104', '내용4');
insert into vms_photo(photo_id, video_id, titl, conts) 
  values(5, 104, 'test5-104', '내용5');

-- 영상 사진 게시글 첨부 파일 예제 데이터
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(1, 1, 'a1.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(2, 2, 'b1.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(3, 2, 'b2.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(4, 2, 'b3.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(5, 3, 'c1.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(6, 3, 'c2.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(7, 4, 'd1.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(8, 5, 'e1.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(9, 5, 'e2.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(10, 5, 'e3.gif');
insert into vms_photo_file(photo_file_id, photo_id, file_path)
  values(11, 5, 'e4.gif');