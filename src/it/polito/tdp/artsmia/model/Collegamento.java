package it.polito.tdp.artsmia.model;

public class Collegamento {
	int objId1;
	int objId2;
	double peso;
	public Collegamento(int objId1, int objId2, double peso) {
		super();
		this.objId1 = objId1;
		this.objId2 = objId2;
		this.peso = peso;
	}
	public int getObjId1() {
		return objId1;
	}
	public void setObjId1(int objId1) {
		this.objId1 = objId1;
	}
	public int getObjId2() {
		return objId2;
	}
	public void setObjId2(int objId2) {
		this.objId2 = objId2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	
	
	
}
