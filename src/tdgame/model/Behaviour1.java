package tdgame.model;

public class Behaviour1 implements IBehaviour{

	//this method increases the speed of the creatures by 16
	@Override
	public int walkSpeed() {
		configModel.walkSpeed=18;
		return walkSpeed();
		
		
	}
	
	}
