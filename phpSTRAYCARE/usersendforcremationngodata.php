<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
  $US_ID=(int)$_POST["us_id"];
    $NGO_ID=(int)$_POST["ngo_id"];
  $ANIMAL_TYPE=$_POST["animal_type"];
  $LOCATION= $_POST["location"];
   
$sql="INSERT INTO SEND_FORCREMATION(US_ID,NGO_ID,ANIMAL_TYPE,LOCATION) VALUES ('$US_ID','$NGO_ID','$ANIMAL_TYPE','$LOCATION')";
$result=mysqli_query($con,$sql);
if($result){
echo "registered succesfully";
} 
else{
echo "some error";
}
?>