<?php
require_once("database.php");
  mysqli_set_charset($con,"utf8");

// $election_state = $_POST['election_state'];
$sql="SELECT id,vehicle_no,mobile_no,feedback FROM feedback";

$result=array();

$data=mysqli_query($con,$sql);

while($row=mysqli_fetch_array($data))
{
	array_push($result,array('id'=>$row[0],'vehicle_no'=>$row[1],'mobile_no'=>$row[2],'feedback'=>$row[3]));
}
echo json_encode(array('getAllFeedback'=>$result));


mysqli_close($con);


?>


]