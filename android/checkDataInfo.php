<?php
    $conn=mysqli_connect('localhost','root','','android');
    $phone=$_POST['PHONE'];
    $PID=$_POST['PERSON_ID'];
    $query="SELECT 
    SUM(CASE WHEN I.PHONE='$phone' THEN 1 ELSE 0 END) AS COLPHONE,
    SUM(CASE WHEN I.PERSON_ID='$PID' THEN 1 ELSE 0 END) AS COLPID 
    FROM (SELECT stuinfo.USER_ID,stuinfo.PHONE,stuinfo.PERSON_ID FROM stuinfo UNION SELECT teachinfo.USER_ID, teachinfo.PHONE,teachinfo.PERSON_ID FROM teachinfo) I";
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