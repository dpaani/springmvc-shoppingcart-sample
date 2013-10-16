package com.codetosalvation.shoppingcart.service.impl;

import com.codetosalvation.shoppingcart.model.PurchaseReport;
import com.codetosalvation.shoppingcart.model.ShoppingCart;
import com.codetosalvation.shoppingcart.service.ReportService;
import com.codetosalvation.shoppingcart.service.SalesTaxService;
import com.codetosalvation.shoppingcart.service.ShoppingCartService;
import com.codetosalvation.shoppingcart.util.NumberUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	SalesTaxService salesTaxService;

	public PurchaseReport generatePurchaseReport() {
		PurchaseReport report = new PurchaseReport();
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();


		double salesTax = salesTaxService.calculateTotalTaxes(shoppingCart);
		double total = salesTaxService.calculateTotalCost(shoppingCart);

		logger.debug("Shopping cart cantains %s items", shoppingCart.getLineItems().size());
		logger.debug("Sales Tax :"+salesTax);
		logger.debug("Total :"+total);

		report.setLineItems(shoppingCart.getLineItems());
		report.setSalesTax(NumberUtils.toBigDecimal(salesTax));
		report.setTotal(NumberUtils.toBigDecimal(total));

		return report;
	}
}
