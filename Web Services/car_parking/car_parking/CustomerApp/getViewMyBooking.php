<?php
require_once("database.php");
  mysqli_set_charset($con,"utf8");

$vehicle_no = $_POST['vehicle_no'];
$sql="SELECT id,book_vehicle_no,plot_name,slot_name,date_of_booking,time_of_booking FROM parking_booking where book_vehicle_no = '$vehicle_no'";

$result=array();

$data=mysqli_query($con,$sql);

while($row=mysqli_fetch_array($data))
{
	array_push($result,array('id'=>$row[0],'book_vehicle_no'=>$row[1],'plot_name'=>$row[2],'slot_name'=>$row[3],'date_of_booking'=>$row[4],'time_of_booking'=>$row[5]));
}
echo json_encode(array('getViewMyBooking'=>$result));


mysqli_close($con);


?>


]