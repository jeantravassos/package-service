package com.jeantravassos.packageservice.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.jeantravassos.packageservice.dto.Package;
import com.jeantravassos.packageservice.exception.APIException;

@ConfigurationProperties("service")
public class Packer {

	private Packer() {
	}

	public static String pack(String filePath) throws APIException {

		if (StringUtils.isEmpty(filePath)) {
			throw new APIException("File Path is invalid : " + filePath);
		}
		
		StringBuilder stringToReturn = new StringBuilder();

		// Try to read the file
		try (Stream<String> stream = Files.lines(Paths.get(filePath), Charset.forName("UTF-8"))) {

			double maxWeight;
			List<Package> packagesList;
			List<String> allLines = stream.filter(string -> !string.isEmpty()).collect(Collectors.toList());
			for (String line : allLines) {

				maxWeight = Integer.parseInt(line.substring(0, line.indexOf(':')).trim());

				String[] packagesArray = StringUtils.substringsBetween(line, "(", ")");

				packagesList = new ArrayList<Package>(packagesArray.length);
				Comparator<Package> priceWeightComparator = Comparator.comparingDouble(Package::getPrice).reversed()
						.thenComparingDouble(Package::getWeight);

				createListOfPackages(packagesArray, packagesList);
				Collections.sort(packagesList, priceWeightComparator);

				stringToReturn.append(packing(maxWeight, packagesList));
				stringToReturn.append("\n");
			}

			return stringToReturn.toString();

		} catch (IOException e) {
			e.printStackTrace();
			throw new APIException("File Path is invalid : " + filePath);
		}
	}

	/**
	 * Receives all the packages of a single line organized and sorted Evaluate
	 * which ones will return and be inserted into the final package
	 * 
	 * @param maxWeight
	 * @param packagesList
	 * @return
	 */
	private static String packing(double maxWeight, List<Package> packagesList) {

		StringBuilder selectedPacks = new StringBuilder();
		boolean first = true;
		for (Package item : packagesList) {

			if (item.getWeight() <= maxWeight) {
				if (!first) {
					selectedPacks.append(",");
				} else {
					first = false;
				}

				selectedPacks.append(item.getIndex());

				maxWeight -= item.getWeight();

				if (maxWeight <= 0) {
					break;
				}
			}
		}

		if (selectedPacks.length() == 0)
			return "-";

		return selectedPacks.toString();
	}

	/**
	 * Receive all items of a single line Validate weight and price according to the
	 * constraints Return a List of Package items so it can be sorted
	 * 
	 * @param packagesArray
	 * @param packagesList
	 */
	private static void createListOfPackages(String[] packagesArray, List<Package> packagesList) throws APIException {

		if (packagesArray != null && packagesArray.length > 0) {

			if (packagesArray.length > 15) {
				throw new APIException("There is more than 15 items in a single line");
			}

			int index;
			double weight;
			double price;

			for (int i = 0; i < packagesArray.length; i++) {

				if (!StringUtils.isEmpty(packagesArray[i].trim())) {

					String[] values = packagesArray[i].split(",");
					if (values.length == 3) {
						index = Integer.parseInt(values[0].trim());
						weight = Double.parseDouble(values[1].trim());
						price = Double.parseDouble(values[2].replaceAll("\\D+", "").trim());

						validateConstraints(weight, price);
						
						Package newPackage = new Package(index, weight, price);
						packagesList.add(newPackage);
					}
				}
			}
		}
	}

	private static void validateConstraints(double weight, double price) throws APIException {
		// TODO - Validate the constraints
		if (weight > 100 ) {
			throw new APIException("Weight is greater than the maximum of 100");
		}
			
		if (price > 100 ) {
			throw new APIException("Price is greater than the maximum of 100");
		}
	}
	
}
