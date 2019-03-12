<?php 

include 'connection.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	
	$db = new Connection();
	$respone = array("error" => false);

	$kode_barang = $_POST['kode_barang'];

	if ($db->DeleteBarang($kode_barang)) {
		
		$respone["error"] = false;
		$respone["message"] = "Data berhasil dihapus";
		echo json_encode($respone);
	} else {
		$respone["error"] = true;
		$respone["message"] = "Data gagal dihapus";
		echo json_encode($respone);
	}
} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}
?>