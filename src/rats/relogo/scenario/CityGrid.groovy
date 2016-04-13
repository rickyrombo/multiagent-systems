package rats.relogo.scenario

class CityGrid {
	
	def static getIntersection(i, worldWidth, worldHeight, minXcor, minYcor) {
		return [x: getAvenueX(Math.floor(i/2), worldWidth, minXcor), y: getStreetY(i % 2, worldHeight, minYcor)];
	}
	
	def static getAvenueX(avenueNumber, worldWidth, minXcor) {
		return Math.round(worldWidth / 3.0 * avenueNumber + minXcor + worldWidth / 6.0);
	}
	
	def static getAvenueN(x, worldWidth, minXcor) {
		return Math.round((x - worldWidth / 6 - minXcor) / (worldWidth / 3) );
	}
	
	def static getStreetY(streetNumber, worldHeight, minYcor) {
		return Math.round(worldHeight / 2.0 * streetNumber + minYcor + worldHeight / 4.0);
	}
	
	def static getStreetN(y, worldHeight, minYcor) {
		return Math.round((y - worldHeight / 4 - minYcor) / (worldHeight / 2) );
	}
	
}
