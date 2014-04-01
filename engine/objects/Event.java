package engine.objects;

public abstract class Event {
	protected GameObject target;
	protected String event;	
	
	//Pælingin hérna er að búa til event sem er í gameobjectinu. þegar mappið er parsað inn, væru þessi event sett á viðkomandi hluti
	//og síðan myndu þau keyrast á réttum tímum. Veit ekki alveg hvernig þetta gæti virkað.Work in progress.
	//spurning um að setja inn trigger, og hafa triggers í öllum gameObjects. 
	//collision events, button triggers... hmm
	public Event(GameObject target, String event){
		this.target = target;
		this.event = event;
	}
	/**
	 * Stop the current object,
	 */
	protected void stop(){
		System.out.println("Stop");
		this.target.stop();
	}
	/**
	 * Makes this object jump.
	 */
	protected void jump(){
		System.out.println("jump");
		this.target.jump();
	}
	/**
	 * Kills this object.
	 * @param other
	 */
	protected void destroy(){
		System.out.println("Destroy this");
		this.target.destroy();
	}
	/**
	 * Complete the map. I have no idea how to implement this to be honest...
	 */
	protected void completeMap(){
		System.out.println("You won this map!");
		this.target.getMap().completeMap();
	}
}
