package Main;

public abstract class AbstractTimeMeasuredFunction {
	private static String TIME_UNIT = "ms";
	private static long NANO_DEVISION_NUMBER = 1000000;

	private StatisticRegistrator register;
	protected Object[] functionsParams;

	protected AbstractTimeMeasuredFunction(final Object[] functionsParams,
			final StatisticRegistrator registrator) {
		this.register = registrator;
		this.functionsParams = functionsParams;
	}

	public Object Execute() {
		long executionStartTime = System.nanoTime();

		Object result = Execute(functionsParams);

		long executionEndTime = System.nanoTime();
		register.Register(GetTaskDescription() + " - execution time",
				(executionEndTime - executionStartTime) / NANO_DEVISION_NUMBER
						+ TIME_UNIT);

		return result;
	}

	protected StatisticRegistrator GetRegistrator() {
		return register;
	}

	protected abstract Object Execute(final Object[] functionsParams);

	protected abstract String GetTaskDescription();
}
