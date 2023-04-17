<?php
     $server="localhost";
     $username="root";
     $DB="android";
     $conn= new mysqli($server,$username,"",$DB);
     $id=$_GET['id'];
     $val = array();
     $query="SELECT NAME,MMH,CLASS,ATTEND  FROM diemdanh INNER JOIN tabsub ON diemdanh.SUB_ID=tabsub.ID WHERE USER_ID='$id'";
     $results = mysqli_query($conn,$query);
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