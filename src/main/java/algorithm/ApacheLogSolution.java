package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApacheLogSolution {

	/**
	 * Given a log file, return IP address(es) which accesses the site most often.
	 * Including test cases
	 */
	static String IPADDRESS_PATTERN = "(\\d{1,3}\\.){3}\\d{1,3}";

	public static Set<String> findTopIpaddress(String[] lines) {

		// String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
		if (lines == null || lines.length == 0) {
			System.out.println("Invalid input...");
			return null;
		}
		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN, Pattern.CASE_INSENSITIVE);
		Map<String, Integer> ipAddressCountMap = new HashMap<>();
		Arrays.stream(lines).forEach(line -> {
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				String ipAddress = matcher.group();// line.split(" ")[0];
				Integer count = ipAddressCountMap.getOrDefault(ipAddress, 0) + 1;
				ipAddressCountMap.put(ipAddress, count);
			} else {
				System.out.println("Invalid IP address while reading line  -- " + line);
			}
		});

		try {
			Integer topCount = ipAddressCountMap.values().stream().mapToInt(v -> v).max()
					.orElseThrow(IllegalArgumentException::new);
			return ipAddressCountMap.entrySet().stream().filter(x -> x.getValue() == topCount).map(Map.Entry::getKey)
					.collect(Collectors.toCollection(HashSet::new));
		} catch (IllegalArgumentException exc) {
			System.out.println("Couldn't find a Valid IP Address in any of the lines!! ");
			return null;
		}
	}

	public static boolean isExpectedResult(Set<String> actual, String[] expected) {
		System.out.println(actual);
		return actual.equals(new HashSet<String>(Arrays.asList(expected)));
	}

	public static void main(String[] args) {
		String ip1 = "10.0.0.1 - log entry 1 11";
		String ip1V1 = " - 10.0.0.1 log entry 1 11";
		String ip2 = "10.0.0.2 - log entry 1 11";
		String ip3 = "10.0.0.3 - log entry 1 11";
		String ip4 = "10.0.0.4 - log entry 1 11";
		String invalidIPV1 = " log entry 1 11.... ";
		String invalidIPV2 = "10.0.0  log entry 1 11.... ";
		String invalidIPV3 = " INVALID IP ";
		
		boolean status = true;
		
		status &= isExpectedResult(findTopIpaddress(new String[] {ip1,ip1,ip2,ip3,ip4}),new String[] {"10.0.0.1"});
		status &= isExpectedResult(findTopIpaddress(new String[] {ip1,ip1V1,ip2,ip3,ip4}),new String[] {"10.0.0.1"});
		status &= isExpectedResult(findTopIpaddress(new String[] {ip1,ip2,ip3,ip4}),new String[] {"10.0.0.1","10.0.0.2","10.0.0.3","10.0.0.4"});
		status &= isExpectedResult(findTopIpaddress(new String[] {ip1,ip1,ip1,ip1,ip1}),new String[] {"10.0.0.1"});
		status &= isExpectedResult(findTopIpaddress(new String[] {ip1,ip1,ip1V1,ip1V1,ip1V1}),new String[] {"10.0.0.1"});
		status &= isExpectedResult(findTopIpaddress(new String[] {ip1,ip2,invalidIPV1,invalidIPV2}),new String[] {"10.0.0.1","10.0.0.2"});
		status &= findTopIpaddress(new String[] {})==null;
		status &= findTopIpaddress(new String[] {invalidIPV1})==null;
		status &= findTopIpaddress(new String[] {invalidIPV1,invalidIPV2,invalidIPV3})==null;
		System.out.println(status);

		
	}

}