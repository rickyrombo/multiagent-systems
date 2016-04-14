package rats.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	public void addGlobalsAndPanelComponents(){
		/**
		 * See Proposal
		 */
		addSlider("aggressivenessThreshold", 0, 0.1, 1, 0.75);
		addSlider("foodCapacity", 0, 1, 5, 3);
		addSlider("foodSourceSpawnFrequency", 0, 10, 500, 10);
	}
}