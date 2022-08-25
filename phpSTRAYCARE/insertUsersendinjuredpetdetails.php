<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
  $US_ID=(int)$_POST["us_id"];
    $DOC_ID=(int)$_POST["doc_id"];
  $ANIMAL_TYPE=$_POST["animal_type"];
  $LOCATION= $_POST["location"];
    $INJURYTYPE= $_POST["injurytype"];
$sql="INSERT INTO SEND_INJUREDPET(US_ID,DOC_ID,ANIMAL_TYPE,LOCATION,INJURYTYPE) VALUES ('$US_ID','$DOC_ID','$ANIMAL_TYPE','$LOCATION','$INJURYTYPE')";
$result=mysqli_query($con,$sql);
if($result){
echo "registered succesfully";
} 
else{
echo "some error";
}
?>