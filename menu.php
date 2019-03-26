<?php 

include 'connection.php';

if ($_SERVER["REQUEST_METHOD"] == "GET") {
	
	$db = new Connection();
	$list = array();
	$menu = $db->LihatMenu();

	if ($menu) {
		foreach ($menu as $row) {
			array_push($list, array('menu' => $row[1]));
		}
	}

	echo ($menu)?
	json_encode(array("error" => 1, "list" => $list)):
	json_encode(array("error" => 2, "list" => "gagal"));
	
} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}
?>