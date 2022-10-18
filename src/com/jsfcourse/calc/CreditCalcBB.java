package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;

import java.text.DecimalFormat;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CreditCalcBB {
	private Double amount;
	private Integer years;
	private Double interest;
	
	private String result;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");

	@Inject
	FacesContext ctx;

	
	
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			
			double amountOfMonths = years* 12;

            double unroundedresult = (amount + amount * (interest / 100)) / amountOfMonths;
            result = df.format(unroundedresult);
			

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}
	
	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata: " + result + " zł", null));
		}
		return null;
	}


	public String info() {
		return "info";
	}
}
