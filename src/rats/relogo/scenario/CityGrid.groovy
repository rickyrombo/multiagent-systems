package rats.relogo.scenario

class CityGrid {
	
	def static worldWidth;
	def static worldHeight;
	def static minXcor;
	def static minYcor;
	
	def static getIntersection(i) {
		return [x: getAvenueX(Math.floor(i/2)), y: getStreetY(i % 2)];
	}
	
	def static getAvenueX(avenueNumber) {
		return Math.round(worldWidth / 3.0 * avenueNumber + minXcor + worldWidth / 6.0);
	}
	
	def static getAvenueN(x) {
		return Math.round((x - worldWidth / 6 - minXcor) / (worldWidth / 3) );
	}
	
	def static getStreetY(streetNumber) {
		return Math.round(worldHeight / 2.0 * streetNumber + minYcor + worldHeight / 4.0);
	}
	
	def static getStreetN(y) {
		return Math.round((y - worldHeight / 4 - minYcor) / (worldHeight / 2) );
	}
	
	def static getAvenueXError(x) {
		def aveN = CityGrid.getAvenueN(x);
		def x_intersection = CityGrid.getAvenueX(aveN);
		def x_err = x_intersection - x;
		return x_err;
	}
	
	def static getStreetYError(y) {
		def streetN = CityGrid.getStreetN(y);
		def y_intersection = CityGrid.getStreetY(streetN);
		def y_err = y_intersection - y;
		return y_err;
	}
}
