<?php
require_once("database.php");
  mysqli_set_charset($con,"utf8");

// $election_state = $_POST['election_state'];
$sql="SELECT id,vehicle_no,name,mobile_no,email_id FROM users";

$result=array();

$data=mysqli_query($con,$sql);

while($row=mysqli_fetch_array($data))
{
	array_push($result,array('id'=>$row[0],'vehicle_no'=>$row[1],'name'=>$row[2],'mobile_no'=>$row[3],'email_id'=>$row[4]));
}
echo json_encode(array('getAllUsers'=>$result));


mysqli_close($con);


?>


]