import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculate the detour distance between two different rides. 
 * 
 * Given four latitude / longitude pairs, 
 * where driver one is traveling from point A to point B and 
 * driver two is traveling from point C to point D, 
 * write a function (in your language of choice) to calculate the shorter of the detour distances
 * the drivers would need to take to pick-up and drop-off the other driver.
 * 
 * @author Ramesh Sakibanda
 * 
 */
public class UberDeTour {
	public static class GeoLocation {
		private double latitude;
		private double longitude;

		public GeoLocation(double i, double j) {
			latitude = i;
			longitude = j;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double x) {
			this.latitude = x;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double y) {
			this.longitude = y;
		}
	}

	private static GeoLocation A = new GeoLocation(0, 0), B = new GeoLocation(
			0, 100), C = new GeoLocation(100, 0),
			D = new GeoLocation(100, 100);
	private static List<String> paths = new ArrayList<String>();
	static Map<String, GeoLocation> GeoLocations = new HashMap<String, GeoLocation>();

	public static void main(String[] args) {
		prepareGeoLocationsMap();
		List<Double> distances = new ArrayList<Double>();
		for (String path : getPossiblePaths()) {
			char[] pitstops = path.toCharArray();
			double distance = 0.0;
			for (int index = 0; index < pitstops.length - 1; index++) {
				distance += distance(GeoLocations.get("" + pitstops[index]),
						GeoLocations.get("" + pitstops[index + 1]));
			}
			distances.add(distance);
		}
		double leastDistance = distances.get(0);
		String bestPath = paths.get(0);
		for (int index = 1; index < paths.size(); index++) {
			if (leastDistance > distances.get(index)) {
				bestPath = paths.get(index);
				leastDistance = distances.get(index);
			}
		}
		System.out.println(bestPath + " is with distance " + leastDistance);
	}

	/**
	 * Initialize locations map
	 */
	private static void prepareGeoLocationsMap() {
		GeoLocations.put("A", A);
		GeoLocations.put("B", B);
		GeoLocations.put("C", C);
		GeoLocations.put("D", D);
	}

	/**
	 * what are the possible ways to traverse the waypoints.
	 * @return list of paths
	 */
	private static List<String> getPossiblePaths() {
		paths = new ArrayList<String>();
		paths.add("ACDB");
		paths.add("CABD");
		return paths;
	}
	
	/**
	 * Calculate distance between to two WayPoints
	 * @param start
	 * @param end
	 * @return distance
	 */
	public static float distance(GeoLocation start, GeoLocation end) {
		double earthRadius = 3958.75;
		double yLatitude = end.getLatitude();
		double xLatitude = start.getLatitude();
		double dLatitude = Math.toRadians(yLatitude - xLatitude);
		double yLongitude = end.getLongitude();
		double xLongitude = start.getLongitude();
		double dLongitude = Math.toRadians(yLongitude - xLongitude);
		double a = Math.sin(dLatitude / 2) * Math.sin(dLatitude / 2)
				+ Math.cos(Math.toRadians(xLatitude))
				* Math.cos(Math.toRadians(yLatitude)) * Math.sin(dLongitude / 2)
				* Math.sin(dLongitude / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		int meterConversion = 1609;

		return new Float(dist * meterConversion).floatValue();
	}
}