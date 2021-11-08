package fr.ensimag.events;

import fr.ensimag.boids.AgentGroup;
import fr.ensimag.interactions.Interaction;

public class AddInteractionEvent extends Event {
	public AgentGroup target;
	public Interaction interaction;
	
	public AddInteractionEvent(long date, AgentGroup target, Interaction interaction) {
		super(date);
		
		this.target = target;
		this.interaction = interaction;
	}

	@Override
	public void execute() {
		this.target.addInteraction(interaction);
	}

}
