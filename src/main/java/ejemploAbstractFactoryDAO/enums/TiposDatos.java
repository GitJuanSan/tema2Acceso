package ejemploAbstractFactoryDAO.enums;

public enum TiposDatos {
	ASIGNATURAS(1), PROFESORES(2);

	private final int valor;

	TiposDatos(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static TiposDatos fromValor(int valor) {
		for (TiposDatos fuente : TiposDatos.values()) {
			if (fuente.getValor() == valor) {
				return fuente;
			}
		}
		return null;
	}
}
