<?php
require_once("database.php");
$vehicle_no=$_POST['vehicle_no'];
$mobile_no=$_POST['mobile_no'];
$feedback=$_POST['feedback'];



$sql="insert into feedback(vehicle_no,mobile_no,feedback) values('$vehicle_no','$mobile_no','$feedback')";
$result=mysqli_query($con,$sql);
if($result>0)
{
$response['success']=1;
}
else
{
	$response['success']=0;
}
echo json_encode($response);

?>