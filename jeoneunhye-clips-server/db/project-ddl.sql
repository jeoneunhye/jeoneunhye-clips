-- 영상 테이블 생성
create table vms_video (
  video_id int not null auto_increment primary key comment '영상 데이터 식별 번호', 
  subject varchar(30) not null comment '영상 주제',
  titl varchar(200) not null comment '영상 제목',
  url varchar(100) not null comment '영상 주소',
  playtime varchar(10) not null comment '영상 재생시간',
  uploader varchar(30) not null comment '영상 업로더',
  upload_dt datetime not null comment '영상 업로드일'
) comment '영상'; 

-- 회원 테이블 생성
create table vms_member (
  member_id int not null auto_increment primary key comment '회원 데이터 식별 번호',
  id varchar(20) not null comment '아이디',
  nickname varchar(20) not null comment '닉네임',
  pwd varchar(30) not null comment '암호',
  phone varchar(30) not null comment '휴대폰 번호',
  email varchar(50) not null comment '이메일',
  cdt datetime default now() comment '회원 등록일' 
) comment '회원';

create unique index UIX_vms_member_id
  on vms_member ( -- 회원
    id asc    -- 아이디
  );

create unique index UIX_vms_member_email
  on vms_member ( -- 회원
    email asc    -- 이메일
  );

-- 게시글 테이블 생성
create table vms_board (
  board_id int not null auto_increment primary key comment '게시물 식별 번호',
  titl varchar(100) not null comment '게시글 제목',
  conts text not null comment '게시글 내용',
  writer varchar(30) not null comment '게시글 작성자',
  cdt datetime default now() comment '게시글 등록일',
  vw_cnt int default 0 comment '조회수' 
) comment '게시글';

-- 영상 사진 게시글 테이블 생성
create table vms_photo (
  photo_id int not null auto_increment primary key comment '사진 게시글 식별 번호',
  video_id int not null comment '영상 번호',
  titl varchar(255) not null comment '사진 게시글 제목',
  conts text comment '사진 게시글 내용',
  cdt datetime default now() comment '사진 게시글 등록일',
  vw_cnt int default 0 comment '조회수',
  -- video_id에 저장되는 값은 vms_video 테이블의 video_id 값으로 제한하는 조건을 추가한다.
  constraint fk_photo_to_video foreign key (video_id)
    references vms_video (video_id)
) comment '사진 게시글';

-- 영상 사진 게시글에 첨부하는 사진 파일 테이블 생성
create table vms_photo_file (
  photo_file_id int not null auto_increment primary key comment '사진 파일 식별 번호',
  photo_id int not null comment '사진 게시글 식별 번호',
  file_path varchar(255) not null comment '사진 파일 경로',
  constraint fk_photo_file_to_photo foreign key (photo_id)
    references vms_photo (photo_id)
) comment '사진 게시글 첨부파일';