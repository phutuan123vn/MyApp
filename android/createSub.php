<?php
    $server="localhost";
    $username="root";
    $DB="android";
    $conn= new mysqli($server,$username,"",$DB);
    $query="DROP TABLE TabSub";
    if($conn->query($query)===true){
        echo "Success drop table TabSub \r\n";
    }else{
        echo "Fail drop table TabSub \r\n ";
    }
    $query="CREATE TABLE TabSub (
        ID INTEGER AUTO_INCREMENT,
        MMH varchar(8),
        NAME VARCHAR(25),
        CLASS VARCHAR(5),
        PRIMARY KEY (ID)
        )";
    if($conn->query($query)===true){
        echo "Success Create table \r\n";
    }else{
        echo "Fail Create table \r\n";
    }
    $query="INSERT INTO TabSub VALUES ('A2','THUC TAP DIEN 2','L02')";
    if($conn->query($query)===true){
        echo "Insert success \r\n";
    }else{
        echo "insert fail \r\n";
    }
    $query="INSERT INTO TabSub (MMH,NAME,CLASS) VALUES ('A1','THUC TAP DIEN 1','L02'),('A3','THUC TAP DIEN 3','L02');";
    if($conn->query($query) === TRUE ){
        echo "Success";
    }else{
        echo "Fail";
    }
    // lan fail
    
?>