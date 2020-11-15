package com.denihidayat.teslsp.sqllite;


public class BarangDataitem {

	private String nama;
	private Double harga;
	private String merk;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setHarga(Double harga){
		this.harga = harga;
	}

	public Double getHarga(){
		return harga;
	}

	public void setMerk(String merk){
		this.merk = merk;
	}

	public String getMerk(){
		return merk;
	}
}