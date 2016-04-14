package rats.relogo.scenario

class CityGrid {
	
	def static worldWidth;
	def static worldHeight;
	def static minXcor;
	def static minYcor;
	
	/**
	 * Grab intersection i
	 * @param i the i to determine the ith intersection
	 * @return dictionary of [x, y]
	 */
	def static getIntersection(i) {
		return [x: getAvenueX(Math.floor(i/2)), y: getStreetY(i % 2)];
	}
	
	/**
	 * Get the X position of the avenueNumber-th avenue
	 * @param avenueNumber
	 * @return int X position
	 */
	def static getAvenueX(avenueNumber) {
		return Math.round(worldWidth / 3.0 * avenueNumber + minXcor + worldWidth / 6.0);
	}
	
	/**
	 * Get the avenue number closest to the given x
	 * @param x the current x position
	 * @return int the nearest avenue number
	 */
	def static getAvenueN(x) {
		return Math.round((x - worldWidth / 6 - minXcor) / (worldWidth / 3) );
	}
	
	/**
	 * Get the Y position of the streetNumber-th
	 * @param streetNumber the street that we want the Y of
	 * @return int the y position of the street
	 */
	def static getStreetY(streetNumber) {
		return Math.round(worldHeight / 2.0 * streetNumber + minYcor + worldHeight / 4.0);
	}
	
	/**
	 * Get the street number closest to the given Y
	 * @param y the y position to test
	 * @return int the street number of the closest street
	 */
	def static getStreetN(y) {
		return Math.round((y - worldHeight / 4 - minYcor) / (worldHeight / 2) );
	}
	
	/**
	 * Get the difference between the given X and the closest avenue
	 * @param x the x position to test
	 * @return int the error
	 */
	def static getAvenueXError(x) {
		def aveN = CityGrid.getAvenueN(x);
		def x_intersection = CityGrid.getAvenueX(aveN);
		def x_err = x_intersection - x;
		return x_err;
	}
	
	/**
	 * Get the difference between the given Y and the closest street
	 * @param y the y position to test
	 * @return int the error
	 */
	def static getStreetYError(y) {
		def streetN = CityGrid.getStreetN(y);
		def y_intersection = CityGrid.getStreetY(streetN);
		def y_err = y_intersection - y;
		return y_err;
	}
}
