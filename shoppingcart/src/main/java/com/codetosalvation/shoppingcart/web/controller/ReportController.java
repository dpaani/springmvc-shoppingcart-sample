package com.codetosalvation.shoppingcart.web.controller;

import com.codetosalvation.shoppingcart.model.PurchaseReport;

import com.codetosalvation.shoppingcart.service.ReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class to generate shopping cart report
 * @author ddakshna
 *
 */
@Controller
@RequestMapping("/report")
public class ReportController {
	Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	ReportService reportService;

	/**
	 * Generates purchase receipt report
	 * @return PurchaseReport contains shopping cart line items
	 * and sales tax total and total cost
	 */
	@RequestMapping("purchase")
	public @ResponseBody PurchaseReport generatePurchaseReport() {
		logger.debug("Generating purchse order receipt report");
		return reportService.generatePurchaseReport();
	}
}
