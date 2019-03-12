<?php 

include 'connection.php';

if ($_SERVER["REQUEST_METHOD"] == "GET") {
	
	$db = new Connection();
	$respone_barang = array();
	$barang = $db->LihatBarang();

	if ($barang) {
		foreach ($barang as $row) {
			array_push($respone_barang, array('kode_barang' => $row[1], 'nama_barang' => $row[2], 'jenis_barang' => $row[3], 'produk_barang' => $row[4], 'harga_barang' => $row[5]));
		}
	}

	echo ($barang)?
	json_encode(array("value" => 1, "respone_barang" => $respone_barang)):
	json_encode(array("value" => 2, "respone_barang" => "gagal menampilkan barang"));
	
} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}
?>