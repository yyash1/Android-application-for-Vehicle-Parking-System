<?php
require_once("database.php");
  mysqli_set_charset($con,"utf8");

$watchmen_id = $_POST['watchmen_id'];
$sql="SELECT id,watchmen_id,watchmen_name,watchmen_mobile_no FROM watchmen where watchmen_id = '$watchmen_id'";

$result=array();

$data=mysqli_query($con,$sql);

while($row=mysqli_fetch_array($data))
{
	array_push($result,array('id'=>$row[0],'watchmen_id'=>$row[1],'watchmen_name'=>$row[2],'watchmen_mobile_no'=>$row[3]));
}
echo json_encode(array('getMyProfile'=>$result));


mysqli_close($con);


?>


]