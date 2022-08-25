<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
      $ngo_id=(int)$_POST["ngo_id"];
    
 $result=array();
 $result['data']=array();
 $select="SELECT * FROM SEND_FORCREMATION INNER JOIN USER ON USER.US_ID=SEND_FORCREMATION.US_ID WHERE NGO_ID='$ngo_id'";
 $response=mysqli_query($con,$select);
while($row=mysqli_fetch_array($response)){
      
    $index['animaltype']=$row['3'];
     $index['location']=$row['4'];
            
    $index['name']=$row['6'];
     $index['email']=$row['7'];
       $index['mobile']=$row['8'];
     array_push($result['data'],$index);
}
$result["success"]="1";
echo json_encode($result);
mysqli_close($connection);
?>
