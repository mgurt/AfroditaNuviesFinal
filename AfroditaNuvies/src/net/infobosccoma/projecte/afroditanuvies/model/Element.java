package net.infobosccoma.projecte.afroditanuvies.model;

abstract public class Element {
	
	
	private int id;
	private String nom, descripcio,imatge;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getImatge() {
		return imatge;
	}
	public void setImatge(String imatge) {
		this.imatge = imatge;
	}
	abstract String getImatgeurl();
	
	public String getImatgethumburl() {
		return getImatgeurl()+"/thumbnails";
	}

}
