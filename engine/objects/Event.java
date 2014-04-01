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
}
