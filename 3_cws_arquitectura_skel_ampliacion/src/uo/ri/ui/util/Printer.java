package uo.ri.ui.util;

import java.util.List;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.dto.CardDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.VoucherDto;
import uo.ri.business.dto.VoucherSummary;

public class Printer {

	public static void printInvoice(InvoiceDto invoice) {

		double importeConIVa = invoice.total;
		double iva = invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Factura nº: %d\n", invoice.number);
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", invoice.date);
		Console.printf("\tTotal: %.2f €\n", importeSinIva);
		Console.printf("\tIva: %.1f %% \n", invoice.vat);
		Console.printf("\tTotal con IVA: %.2f €\n", invoice.total);
		Console.printf("\tEstado: %s\n", invoice.status);
	}

	public static void printPaymentMeans(List<PaymentMeanDto> medios) {
		Console.println();
		Console.println("Medios de pago disponibles");

		Console.printf("\t%s \t%-8.8s \t%s \t%s \t%s\n", "ID", "Tipo",
				"Acumulado", "Disponible", "Válidez");
		for (PaymentMeanDto medio : medios) {
			printTypePaymentMean(medio);
		}
	}

	private static void printTypePaymentMean(PaymentMeanDto medio) {
		switch (medio.getClass().getName()) {
		case "uo.ri.business.dto.CashDto":
			Console.printf("\t%s \t%-8.8s \t%s \n", medio.id, "Metálico",
					medio.accumulated);
			break;
		case "uo.ri.business.dto.VoucherDto":
			Console.printf("\t%s \t%-8.8s \t%s \t\t%s €\n", medio.id, "Bono",
					medio.accumulated, ((VoucherDto) medio).available);
			break;
		case "uo.ri.business.dto.CardDto":
			Console.printf("\t%s \t%-8.8s \t%s \t\t\t\t%s\n", medio.id,
					"Tarjeta", medio.accumulated,
					DateUtil.toString(((CardDto) medio).cardExpiration));
			break;
		default:
			Console.print("");
			break;
		}
	}

	public static void printVoucherDtos(List<VoucherDto> medios) {
		Console.println();
		Console.println("Medios de pago disponibles");

		Console.printf("\t%s \t%-8.8s \t%s \t%s \t%s\n", "ID", "Tipo",
				"Acumulado", "Disponible", "Válidez");
		for (VoucherDto medio : medios) {
			Console.printf("\t%s \t%-8.8s \t%s \t\t%s €\n", medio.id, "Bono",
					medio.accumulated, ((VoucherDto) medio).available);
		}
	}

	public static void printRepairing(BreakdownDto rep) {

		Console.printf("\t%d \t%-40.40s \t%td/%<tm/%<tY \t%-12.12s \t%.2f\n",
				rep.id, rep.description, rep.date, rep.status, rep.total);
	}

	public static void printMechanic(MechanicDto m) {

		Console.printf("\t%d %-10.10s %-25.25s %-25.25s\n", m.id, m.dni, m.name,
				m.surname);
	}

	public static void printVoucherSummary(List<VoucherSummary> a) {
		for (VoucherSummary v : a) {
			Console.printf("\t%s %s %s %d %f %f %f\n", v.dni, v.name, v.surname,
					v.emitted, v.consumed, v.available);
		}
	}

}
