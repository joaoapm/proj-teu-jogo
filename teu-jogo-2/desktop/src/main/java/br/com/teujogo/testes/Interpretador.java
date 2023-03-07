package br.com.teujogo.testes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

import br.com.teujogo.ed.Elemento;
import br.com.teujogo.enumeration.TipoVariaveis;

public class Interpretador {

	private TipoVariaveis variavel;

	private String codigo = "F = m * g";

	public Interpretador() {

		if (verificaSintaxe()) {
			setVariavel();
			setValores();
		}

	}

	private void setValores() {
		String codigoAt = codigo.split("=")[1].trim();
		String codigoProc = codigoAt;
		Elemento e = new Elemento();

		Expression expression = new Expression(codigoProc);
		EvaluationValue result;
		
		for (TipoVariaveis varAt : TipoVariaveis.values()) {

			if (varAt.equals(TipoVariaveis.MASSA))
				expression.with(varAt.getLabel(), 2);

			else if (varAt.equals(TipoVariaveis.CONST_GRAV))
				expression.with(varAt.getLabel(), 5);

		}
		
		try {
			result = expression.evaluate();
			System.out.println(result.getNumberValue().doubleValue());
		} catch (EvaluationException | ParseException e1) {
			e1.printStackTrace();
		} 

	}

	private void setVariavel() {
		String var = codigo.split("=")[0].trim();
		if (TipoVariaveis.getLabelList().contains(var)) {
			variavel = TipoVariaveis.getElementoByLabel(var);
		}

	}

	private boolean verificaSintaxe() {
		final Pattern pattern = Pattern.compile("\\b[A-Z0-9]+\\b *= *[a-zA-Z0-9]+");
		final Matcher matcher = pattern.matcher(this.codigo);
		return matcher.find();
	}

	public static void main(String args[]) {
		new Interpretador();
	}
}
