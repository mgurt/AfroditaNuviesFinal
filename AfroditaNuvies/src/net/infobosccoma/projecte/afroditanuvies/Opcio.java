package net.infobosccoma.projecte.afroditanuvies;

import android.media.Image;

public class Opcio {
	private Image imatge;
	private String text;
	
	public Opcio(Image imatge, String text) {
		super();
		this.imatge = imatge;
		this.text = text;
	}
	public Image getImatge() {
		return imatge;
	}
	public void setImatge(Image imatge) {
		this.imatge = imatge;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
