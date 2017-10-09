package modelo;

/**
 * @author Orlando
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Advogado extends Funcionario{

	int Id;
	String oab;
	
	//retorna oab
	public String getOab() {
		return oab;
	}
	
	//seta oab
	public void setOab(String oab) {
		this.oab = oab;
	}
}
