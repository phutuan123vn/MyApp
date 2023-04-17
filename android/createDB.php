<?php
    $server="localhost";
    $username="root";
    $DB="android";
    $conn= new mysqli($server,$username,"",$DB);
    if($conn->query("DROP TABLE user, StuInfo, TeachInfo")===TRUE){
        echo "DELETE PREVIOUS TABLE SUCCESS \r\n"."<br />";
    }else{
        echo "FAILED DEL TABLE \r\n"."<br />";
    }
    $cmd="CREATE TABLE user (ID INTEGER AUTO_INCREMENT PRIMARY KEY, LAST_NAME TEXT, FIRST_NAME TEXT, EMAIL TEXT, PASSWORD TEXT, ROLE TEXT )";
    if($conn->query($cmd) === TRUE){
        echo "Success CREATE TABLE USER \r\n"."<br />" ;
    }else{
        echo "Fail CREATE TABLE USER \r\n"."<br />";
    }
    $cmd="CREATE TABLE StuInfo (
        USER_ID INTEGER AUTO_INCREMENT,
        CAREER VARCHAR(4),
        CODE INT(6) ZEROFILL DEFAULT '0',
        PHONE VARCHAR(11),
        ADDRESS TEXT,
        PERSON_ID VARCHAR(11),
        PRIMARY KEY (USER_ID)
    ) ";
        if($conn->query($cmd)===TRUE){
            echo "SUCCESS CREATE TABLE INFO \r\n"."<br />";
        }else{
            echo "FAIL CREATE TABLE INFO \r\n"."<br />";
        }
    //
    $cmd="CREATE TABLE TeachInfo (
        USER_ID INTEGER AUTO_INCREMENT,
        CAREER VARCHAR(4),
        CODE INT(6) ZEROFILL DEFAULT '0',
        PHONE VARCHAR(11),
        ADDRESS TEXT,
        PERSON_ID VARCHAR(11),
        SUBJECT VARCHAR(20),
        PRIMARY KEY (USER_ID)
    ) ";
        if($conn->query($cmd)===TRUE){
            echo "SUCCESS CREATE TABLE TEACH_INFO \r\n"."<br />";
        }else{
            echo "FAIL CREATE TABLE TEACH_INFO \r\n"."<br />";
        }
    //
    $cmd="ALTER TABLE StuInfo ADD FOREIGN KEY (USER_ID) REFERENCES user(ID) ON UPDATE CASCADE ON DELETE CASCADE ";
    if($conn->query($cmd)===TRUE){
        echo "SUCCESS FOREIGN STU \r\n"."<br />";
    }else{
        echo "FAIL FOREIGN STU  \r\n"."<br />";
    }
    //
    $cmd="ALTER TABLE TeachInfo ADD FOREIGN KEY (USER_ID) REFERENCES user(ID) ON UPDATE CASCADE ON DELETE CASCADE ";
    if($conn->query($cmd)===TRUE){
        echo "SUCCESS FOREIGN TEACH \r\n"."<br />";
    }else{
        echo "FAIL  FOREIGN TEACH \r\n"."<br />";
    }
    //sinhvien
    $cmd="INSERT INTO user (EMAIL, PASSWORD, ROLE ) VALUES ('sinhvien', '123', '0')";
    if($conn->query($cmd)===TRUE){
        echo "INSERT SINH VIEN SUCCESS \r\n"."<br />";
    }else{
        echo "INSERT SINH VIEN FAIL \r\n"."<br />";
    }
    //giangviern
    $cmd="INSERT INTO user (EMAIL, PASSWORD, ROLE ) VALUES ('giangvien', '123', '1')";
    if($conn->query($cmd)===TRUE){
        echo "INSERT GIANG VIEN SUCCESS \r\n"."<br />";
    }else{
        echo "INSERT GIANG VIEN FAIL \r\n"."<br />";
    }
    // quan ly
    $cmd="INSERT INTO user (EMAIL, PASSWORD, ROLE ) VALUES ('admin', '123', '2')";
    if($conn->query($cmd)===TRUE){
        echo "INSERT QUAN LY SUCCESS \r\n"."<br />";
    }else{
        echo "INSERT QUAN LY FAIL \r\n"."<br />";
    }
    //trigger insert
    $cmd="CREATE TRIGGER `insertid` AFTER INSERT ON `user`
    FOR EACH ROW IF NEW.ROLE=0 THEN
        INSERT INTO stuinfo (USER_ID,CAREER) VALUES (NEW.ID,'SV');
        UPDATE stuinfo SET CODE=(SELECT COUNT(USER_ID) FROM stuinfo) WHERE USER_ID=NEW.ID;
      ELSEIF NEW.ROLE=1 THEN 
       INSERT INTO teachinfo (USER_ID,CAREER) VALUES (NEW.ID,'GV');
       UPDATE teachinfo SET CODE=(SELECT COUNT(USER_ID) FROM teachinfo) WHERE USER_ID=NEW.ID;
      END IF";
    if($conn->query($cmd)===TRUE){
        echo "TRIGGER SUCCESS \r\n"."<br />";
    }else{
        echo "TRIGGER FAIL \r\n"."<br />";
    }
    $conn->close();
?>