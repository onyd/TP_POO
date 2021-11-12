package fr.ensimag.events;

import fr.ensimag.boids.AgentGroup;
import fr.ensimag.interactions.Interaction;

/**
 * Represents an event which add an interaction to an group of agents at the
 * wanted date
 *
 */
public class AddInteractionEvent extends Event {
	public AgentGroup target;
	public Interaction interaction;

	/**
	 * Create the event which will add interaction to target
	 * 
	 * @param date
	 * @param target
	 * @param interaction
	 */
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
