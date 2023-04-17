<?php
    $server="localhost";
    $username="root";
    $DB="android";
    $conn= new mysqli($server,$username,"",$DB);
    $ID=$_POST['ID'];
    $query="SELECT `LAST_NAME`,`FIRST_NAME`,`ADDRESS`,`PERSON_ID`,`PHONE`,`EMAIL`,CONCAT(CAREER,CODE),`SUBJECT` FROM user,teachinfo WHERE ID = '$ID' ";
    $results = mysqli_query($conn,$query);
    $val = array();
    if($conn -> connect_error){
        die ("Connection error: ".$conn -> connect_error);
    }else{
        if( mysqli_num_rows($results)>0){
            while($res = mysqli_fetch_assoc($results)){
                $val['response']= "Success";
                $val['data'][] = $res;
                
            }
            echo json_encode($val);
        }else{
            $val['response'] = "Fail";
            echo json_encode($val);
        }
    }
?>