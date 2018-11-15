package cardgame;

public class Card {
	
	private String färg;
	private String valör;
	private int värde;
	
	public Card () {
		
	}
	
	@Override
	public String toString () {
		
		return String.format("%s %s", färg, valör);	
	}
	
	public int getVärde () {
		return värde;
		
	}
	
	public void setFärg (String färg) {
		this.färg = färg;
	}
	
	public void setValör (String valör) {
		this.valör = valör;
	}
	
	public void setVärde (int värde) {
		this.värde = värde;
	}
	
	public void ändraEssVärde () {
		if (värde == 11) {
			värde = 1;
		}
	}
	
	

}
