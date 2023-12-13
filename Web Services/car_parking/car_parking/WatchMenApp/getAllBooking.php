<?php
require_once("database.php");
  mysqli_set_charset($con,"utf8");

// $election_state = $_POST['election_state'];
$sql="SELECT id,book_vehicle_no,plot_name,slot_name,booked,date_of_booking,time_of_booking FROM parking_booking";

$result=array();

$data=mysqli_query($con,$sql);

while($row=mysqli_fetch_array($data))
{
	array_push($result,array('id'=>$row[0],'book_vehicle_no'=>$row[1],'plot_name'=>$row[2],'slot_name'=>$row[3],'booked'=>$row[4],'date_of_booking'=>$row[5],'time_of_booking'=>$row[6]));
}
echo json_encode(array('getAllBooking'=>$result));


mysqli_close($con);


?>


]