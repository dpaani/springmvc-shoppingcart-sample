package com.codetosalvation.shoppingcart.parser;

import com.codetosalvation.shoppingcart.model.InvoiceFileLineItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.Assert;

public class InvoiceDataParser extends AbstractDataParser<InvoiceFileLineItem> {

	public static final String SPACE = " ";
	public static final String IMPORTED_TEXT = "imported";

	public InvoiceDataParser(List<String> lines) {
		super(lines);
	}

	@Override
	protected List<InvoiceFileLineItem> parse() {
		final List<InvoiceFileLineItem> list = new ArrayList<InvoiceFileLineItem>();

		for (final String line : lines) {

			validateLineItem(line);

			final String array[] = StringUtils.split(line, InvoiceDataParser.SPACE);
			final LinkedList<String> elementList = new LinkedList<String>(Arrays.asList(array));

			final int quantity = getQuantity(elementList);
			final boolean isImported = isImported(elementList);
			final double unitCost = getUnitCost(elementList);
			final String productDescription = getDescription(elementList);

			list.add(new InvoiceFileLineItem(quantity, isImported, productDescription, unitCost));
		}
		return list;
	}

	private void validateLineItem(String line) {
		Assert.hasText(line, "data line item must be not-null");
	}

	private double getUnitCost(LinkedList<String> elementList) {
		return NumberUtils.toDouble(elementList.pollLast());
	}

	private String getDescription(LinkedList<String> elementList) {
		elementList.pollLast();
		return StringUtils.join(elementList.iterator(), InvoiceDataParser.SPACE);
	}

	private int getQuantity(LinkedList<String> elementList) {
		return NumberUtils.toInt(elementList.pollFirst());
	}

	private boolean isImported(LinkedList<String> elementList) {
		return elementList.contains(InvoiceDataParser.IMPORTED_TEXT);
	}
}
