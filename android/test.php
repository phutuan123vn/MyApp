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
        MMH varchar(8),
        NAME VARCHAR(25),
        CLASS VARCHAR(5),
        PRIMARY KEY (MMH)
        );";
    if($conn->query($query)===true){
        echo "Success Create table \r\n";
    }else{
        echo "Fail Create table \r\n";
    }
    $query="INSERT INTO TabSub VALUES ('A2','THUC TAP DIEN 2','L02')";
    if($conn->query($query) === TRUE ){
        echo "Success";
    }else{
        echo "Fail";
    }
?>