<?php
require_once("database.php");
$vehicle_no=$_POST['vehicle_no'];
$plot_name=$_POST['plot_name'];
$slot_name=$_POST['slot_name'];
$date_of_booking=$_POST['date_of_booking'];
$time_of_booking=$_POST['time_of_booking'];


$sql="insert into all_remove_booking(vehicle_no,plot_name,slot_name,date_of_booking,time_of_booking) values('$vehicle_no','$plot_name','$slot_name','$date_of_booking','$time_of_booking')";
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