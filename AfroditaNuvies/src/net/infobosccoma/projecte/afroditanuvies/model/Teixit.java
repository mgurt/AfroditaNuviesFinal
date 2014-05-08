package net.infobosccoma.projecte.afroditanuvies.model;

public class Teixit {
	private final static String IMATGEURL = "/img/teixits";
	private final static String IMATGETHUMBURL = IMATGEURL+"/thumbnails";
	
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
	public static String getImatgeurl() {
		return IMATGEURL;
	}
	public static String getImatgethumburl() {
		return IMATGETHUMBURL;
	}

}
