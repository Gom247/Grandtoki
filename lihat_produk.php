<?php 

include "connection.php";
header('Content-Type: application/json');

if ($_SERVER["REQUEST_METHOD"] == "POST") {

	$jenis_barang = $_POST['jenis_barang'];

	$db = new Connection();
	$data_produk = array();
	$barang = $db->LihatJenisBarang($jenis_barang);

	if ($barang) {
		foreach ($barang as $row) {
			array_push($data_produk, array('kode_barang' => $row[1], 'nama_barang' => $row[2], 'jenis_barang' => $row[3], 'produk_barang' => $row[4], 'harga_barang' => $row[5]));
		}
	}

	echo ($barang)?
	json_encode(array("error" => 1, "data_produk" => $data_produk)):
	json_encode(array("error"=>2, "data_produk" => "gagal"));
	
		
}

?>