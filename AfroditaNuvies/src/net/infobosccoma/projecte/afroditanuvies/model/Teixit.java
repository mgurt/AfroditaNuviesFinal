package net.infobosccoma.projecte.afroditanuvies.model;

public class Teixit extends Element{
	
	@Override
	public String getImatgeurl() {
		return "/img/teixits/";
	}
	public Teixit() {

	}

	public Teixit(int id, String nom, String descripcio, String imatge) {
		super(id, nom, descripcio, imatge);
	}
}
