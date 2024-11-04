package ejemploAbstractFactoryDAO.enums;

public enum FuentesDeDatos {
	MYSQL(1), XML(2);

	private final int valor;

	FuentesDeDatos(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static FuentesDeDatos fromValor(int valor) {
		for (FuentesDeDatos fuente : FuentesDeDatos.values()) {
			if (fuente.getValor() == valor) {
				return fuente;
			}
		}
		return null;
	}
}
