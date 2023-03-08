package br.com.teujogo.testes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;

import br.com.teujogo.ed.Elemento;
import br.com.teujogo.ed.Regra;
import br.com.teujogo.enumeration.TipoVariaveis;

public class Interpretador {

	private static TipoVariaveis variavel;
	private static Elemento elemento;
	private String codigo = "F = m * g";

	public Interpretador() {

		if (verificaSintaxe(codigo)) {
			setVariavel(codigo);
			setValores(codigo);
		}

	}

	public static void interpretarRegra(Regra regra, Elemento elementoF) {
		elemento = elementoF;
		if (verificaSintaxe(regra.getRegra())) {
			setVariavel(regra.getRegra());
			setValores(regra.getRegra());
		}
	}

	private static void setVariavel(String codigo) {
		String var = codigo.split("=")[0].trim();
		if (TipoVariaveis.getLabelList().contains(var)) {
			variavel = TipoVariaveis.getElementoByLabel(var);
		}

	}

	private static void setValores(String codigo) {
		String codigoAt = codigo.split("=")[1].trim();
		String codigoProc = codigoAt;

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

			CharacterControl cc = (CharacterControl) elemento.getGeometry().getControl(0);

			if (TipoVariaveis.GRAVIDADE.equals(variavel))
				cc.setGravity(new Vector3f(0, -1 * Float.parseFloat(result.getStringValue()), 0));

		} catch (EvaluationException | ParseException e1) {
			e1.printStackTrace();
		}

	}

	private static boolean verificaSintaxe(String codigo) {
		final Pattern pattern = Pattern.compile("\\b[A-Z0-9]+\\b *= *[a-zA-Z0-9]+");
		final Matcher matcher = pattern.matcher(codigo);
		return matcher.find();
	}

	public static void main(String args[]) {
		new Interpretador();
	}
}
