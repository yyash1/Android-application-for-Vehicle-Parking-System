<?php
require_once("database.php");
  mysqli_set_charset($con,"utf8");

// $election_state = $_POST['election_state'];
$sql="SELECT id,vehicle_no,plot_name,slot_name,date_of_booking,end_date_of_booking,time_of_booking,end_time_of_booking FROM all_booking";

$result=array();

$data=mysqli_query($con,$sql);

while($row=mysqli_fetch_array($data))
{
	array_push($result,array('id'=>$row[0],'vehicle_no'=>$row[1],'plot_name'=>$row[2],'slot_name'=>$row[3],'start_date_of_booking'=>$row[4]
		,'end_date_of_booking'=>$row[5],'start_time_of_booking'=>$row[6],'end_time_of_booking'=>$row[7]));
}
echo json_encode(array('getAllViewBooking'=>$result));


mysqli_close($con);


?>


]