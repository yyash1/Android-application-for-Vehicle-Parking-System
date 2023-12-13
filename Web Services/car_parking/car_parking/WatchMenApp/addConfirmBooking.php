<?php
require_once("database.php");
$vehicle_no=$_POST['vehicle_no'];
$plot_name=$_POST['plot_name'];
$slot_name=$_POST['slot_name'];
$booked=$_POST['booked'];
$date=$_POST['date'];
$time=$_POST['time'];



$sql="insert into parking_booking(book_vehicle_no,plot_name,slot_name,booked,date_of_booking,time_of_booking) values('$vehicle_no','$plot_name','$slot_name','$booked','$date','$time')";
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