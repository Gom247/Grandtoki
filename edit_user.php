<?php 

include 'connection.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	
	$db = new Connection();
	$respone = array("error" => false);

	$email = $_POST['email'];
	$photo = $_POST['photo'];
	$nama = $_POST['nama'];
	$alamat = $_POST['alamat'];
	$notlp = $_POST['notlp'];

	if ($db->UpdateUser($email, $photo, $nama, $alamat, $notlp)) {
		$respone["error"] = false;
		$respone["message"] = "Update data user berhasil";
		echo json_encode($respone);
	} else {
		$respone["error"] = true;
		$respone["message"] = "Update data user gagal";
		echo json_encode($respone);
	}

} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}
?>