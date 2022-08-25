<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
  $US_ID=(int)$_POST["us_id"];
    $NGO_ID=(int)$_POST["ngo_id"];
  $AMOUNT=$_POST["amount"];
  $PICKUPLOCATION= $_POST["pickuplocation"];
    
$sql="INSERT INTO DONATION(US_ID,NGO_ID,AMOUNT,PICKUPLOCATION) VALUES ('$US_ID','$NGO_ID','$AMOUNT','$PICKUPLOCATION')";
$result=mysqli_query($con,$sql);
if($result){
echo "registered succesfully";
} 
else{
echo "some error";
}
?>